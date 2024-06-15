import React, {useEffect, useRef, useState} from 'react';
import {Alert, Box, Button, CircularProgress, Snackbar, Typography} from '@mui/material';
import EpigramForm from '../EpigramForm/EpigramForm';
import axios from "axios";

const Epigram = () => {
    const [text, setText] = useState('');
    const [author, setAuthor] = useState('');
    const [progress, setProgress] = useState(100);
    const [isPaused, setIsPaused] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const intervalRef = useRef(null);
    const textRef = useRef(null);
    const [alert, setAlert] = useState({open: false, message: '', severity: 'success'});

    const fetchNewEpigram = async () => {
        setIsLoading(true);
        try {
            const response = await axios.get("http://localhost:8080/api/v1/epigrams/random");
            const jsonResponse = response.data;

            setAuthor(jsonResponse.author);
            setText(jsonResponse.text);
            setIsLoading(false);

        } catch (error) {
            setIsLoading(false);
            if (error.response && error.response.status === 404) {
                setAlert({open: true, message: 'Epigram Not Found', severity: 'warning'});
            } else {
                setAlert({open: true, message: error.message, severity: 'warning'});
            }
        }
    };

    useEffect(() => {
        fetchNewEpigram();
    }, []);

    const startInterval = () => {
        intervalRef.current = setInterval(() => {
            setProgress((prev) => {
                if (prev === 5) {
                    textRef.current.classList.remove('fadeInText');
                    textRef.current.classList.add('fadeOutText');
                }
                if (prev === 0) {
                    textRef.current.classList.remove('fadeOutText');
                    textRef.current.classList.add('fadeInText');
                    fetchNewEpigram();
                    return 100;
                }
                return prev - 1;
            });
        }, 200);
    };

    useEffect(() => {
        if (!isPaused) {
            startInterval();
        }
        return () => clearInterval(intervalRef.current);
    }, [isPaused]);

    const handlePause = () => {
        setIsPaused(true);
        clearInterval(intervalRef.current);
    };

    const handleContinue = () => {
        setIsPaused(false);
    };

    const handleRefresh = () => {
        fetchNewEpigram();
        setProgress(100);
    };

    const handleCloseAlert = () => setAlert({...alert, open: false});

    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                minHeight: '100vh',
                textAlign: 'center',
            }}
        >
            <Box sx={{mb: 4, maxWidth: 600}}>
                <Box
                    ref={textRef}
                    className="fadeInText"
                    sx={{transition: 'opacity 0.5s'}}
                >
                    <Typography variant="h6" gutterBottom>
                        {text}
                    </Typography>
                    <Typography variant="subtitle1" color="textSecondary">
                        {author}
                    </Typography>
                </Box>
                <Box
                    sx={{
                        width: '100%',
                        height: 10,
                        backgroundColor: '#e0e0e0',
                        borderRadius: 5,
                        overflow: 'hidden',
                        mt: 2,
                    }}
                >
                    <Box
                        sx={{
                            width: `${progress}%`,
                            height: '100%',
                            backgroundColor: '#3f51b5',
                            transition: 'width 0.2s',
                        }}
                    />
                </Box>
            </Box>
            <Box sx={{display: 'flex', justifyContent: 'center', gap: 1, mb: 4}}>
                <Button
                    onClick={handlePause}
                    disabled={isPaused}
                    variant="contained"
                    color="warning"
                >
                    Pause
                </Button>
                <Button
                    onClick={handleContinue}
                    disabled={!isPaused}
                    variant="contained"
                    color="success"
                >
                    Continue
                </Button>
                <Button
                    onClick={handleRefresh}
                    disabled={isLoading}
                    variant="contained"
                    color="info"
                    startIcon={isLoading ? <CircularProgress size={20}/> : null}
                >
                    Refresh
                </Button>
            </Box>
            <Box sx={{width: '100%', maxWidth: 600}}>
                <EpigramForm/>
            </Box>
            <Snackbar open={alert.open} autoHideDuration={6000} onClose={handleCloseAlert}>
                <Alert onClose={handleCloseAlert} severity={alert.severity} sx={{width: '100%'}}>
                    {alert.message}
                </Alert>
            </Snackbar>
        </Box>
    );
};

export default Epigram;

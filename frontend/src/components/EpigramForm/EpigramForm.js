import React, {useState} from 'react';
import {Alert, Box, Button, Snackbar, TextField, Typography} from '@mui/material';
import axios from "axios";

const EpigramForm = () => {
    const [text, setText] = useState('');
    const [author, setAuthor] = useState('');
    const [alert, setAlert] = useState({open: false, message: '', severity: 'success'});
    const [errors, setErrors] = useState({author: '', text: ''});

    const validate = () => {
        let tempErrors = {author: '', text: ''};
        if (author.trim().length > 0 && author.trim().length < 3) {
            tempErrors.author = 'Author name must be at least 3 characters long';
        }
        if (text.trim().length < 3) {
            tempErrors.text = 'Epigram text must be at least 3 characters long';
        }
        setErrors(tempErrors);
        return Object.values(tempErrors).every(x => x === '');
    };

    const saveEpigram = async (event) => {
        event.preventDefault();
        if (!validate()) {
            setAlert({open: true, message: 'Please fix the validation errors', severity: 'warning'});
            return;
        }
        try {
            await axios.post("http://localhost:8080/api/v1/epigrams", {
                text,
                author,
            }, {
                headers: {
                    "Content-Type": "application/json",
                },
            });
            setAuthor("");
            setText("");
            setAlert({
                open: true,
                message: 'Epigram has been added successfully. You will see if you are lucky',
                severity: 'success'
            });
        } catch (error) {
            Object.values(error.response.data).forEach(errorMessage => setAlert({
                open: true,
                message: errorMessage,
                severity: 'error'
            }))
        }
    };

    const handleTextChange = e => {
        setText(e.target.value);
        setErrors({...errors, text: ''});
    };
    const handleAuthorChange = e => {
        setAuthor(e.target.value);
        setErrors({...errors, author: ''});
    };
    const handleCloseAlert = () => setAlert({...alert, open: false});

    return (
        <Box component="form" onSubmit={saveEpigram}
             sx={{
                 display: 'flex',
                 flexDirection: 'column',
                 gap: 2,
                 width: '100%',
                 maxWidth: '600px',
                 mx: 'auto',
                 mt: 4
             }}>
            <Typography variant="h5" component="div" gutterBottom>
                Add a New Epigram
            </Typography>
            <TextField
                label="Author"
                variant="outlined"
                value={author}
                onChange={handleAuthorChange}
                placeholder="Enter author name"
                data-testid="author"
                inputProps={{maxLength: 255}}
                error={Boolean(errors.author)}
                helperText={errors.author}
            />
            <TextField
                label="Text"
                variant="outlined"
                value={text}
                rows={3}
                onChange={handleTextChange}
                placeholder="Enter epigram text"
                multiline
                inputProps={{maxLength: 1024}}
                error={Boolean(errors.text)}
                helperText={errors.text}
                required
                data-testid="text"
            />
            <Button
                type="submit"
                variant="contained"
                color="success"
                data-testid="submit"
            >
                Save Epigram
            </Button>
            <Snackbar open={alert.open} autoHideDuration={6000} onClose={handleCloseAlert}>
                <Alert onClose={handleCloseAlert} severity={alert.severity} sx={{width: '100%'}}>
                    {alert.message}
                </Alert>
            </Snackbar>
        </Box>
    );
}

export default EpigramForm;

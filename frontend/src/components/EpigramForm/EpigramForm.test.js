import React from 'react';
import {fireEvent, render, screen, waitFor} from '@testing-library/react';
import '@testing-library/jest-dom';
import EpigramForm from './EpigramForm';
import axios from 'axios';

jest.mock('axios');

describe('EpigramForm', () => {
    beforeEach(() => {
        axios.post.mockClear();
    });

    test('renders form with fields and submit button', () => {
        render(<EpigramForm/>);

        expect(screen.getByLabelText(/Author/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Text/i)).toBeInTheDocument();
        expect(screen.getByTestId('submit')).toBeInTheDocument();
    });

    test('validates fields and shows error messages', async () => {
        render(<EpigramForm/>);

        fireEvent.change(screen.getByLabelText(/Author/i), {target: {value: 'Al'}});
        fireEvent.change(screen.getByLabelText(/Text/i), {target: {value: 'Hi'}});

        fireEvent.click(screen.getByTestId('submit'));

        expect(await screen.findByText(/Author name must be at least 3 characters long/i)).toBeInTheDocument();
        expect(await screen.findByText(/Epigram text must be at least 3 characters long/i)).toBeInTheDocument();
    });

    test('submits form with valid data', async () => {
        axios.post.mockResolvedValue({data: {message: 'Epigram has been added successfully. You will see if you are lucky'}});

        render(<EpigramForm/>);

        fireEvent.change(screen.getByLabelText(/Author/i), {target: {value: 'Author'}});
        fireEvent.change(screen.getByLabelText(/Text/i), {target: {value: 'This is an epigram'}});

        fireEvent.click(screen.getByTestId('submit'));

        await waitFor(() => {
            expect(axios.post).toHaveBeenCalledWith(
                "http://localhost:8080/api/v1/epigrams",
                {author: 'Author', text: 'This is an epigram'},
                {headers: {"Content-Type": "application/json"}}
            );
        });

        await waitFor(() => {
            expect(screen.getByText((content, element) => content.includes('Epigram has been added successfully. You will see if you are lucky'))).toBeInTheDocument();
        });
    });

    test('handles server error correctly', async () => {
        axios.post.mockRejectedValue({response: {data: {error: 'Server error'}}});

        render(<EpigramForm/>);

        fireEvent.change(screen.getByLabelText(/Author/i), {target: {value: 'Author'}});
        fireEvent.change(screen.getByLabelText(/Text/i), {target: {value: 'This is an epigram'}});

        fireEvent.click(screen.getByTestId('submit'));

        expect(await screen.findByText(/Server error/i)).toBeInTheDocument();
    });
});

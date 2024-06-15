import {render, screen, waitFor} from '@testing-library/react';
import '@testing-library/jest-dom';
import App from './App';
import axios from 'axios';

// Mock the axios module to avoid actual HTTP requests in tests
jest.mock('axios');

describe('App Component', () => {
    beforeEach(() => {
        // Clear all instances and calls to the mock before each test
        axios.get.mockClear();
    });

    test('renders Epigram component and fetches new epigram', async () => {
        // Mock the axios GET request to return a specific response
        axios.get.mockResolvedValueOnce({
            data: {
                author: 'Author Name',
                text: 'Sample epigram text.',
            },
        });

        // Render the App component
        render(<App/>);

        // Check for the initial loading state
        expect(screen.getByRole('button', {name: /pause/i})).toBeInTheDocument();
        expect(screen.getByRole('button', {name: /continue/i})).toBeInTheDocument();
        expect(screen.getByRole('button', {name: /refresh/i})).toBeInTheDocument();

        // Wait for the epigram text to be rendered
        await waitFor(() => {
            expect(screen.getByText(/Sample epigram text./i)).toBeInTheDocument();
            expect(screen.getByText(/Author Name/i)).toBeInTheDocument();
        });

        // Check if the EpigramForm is rendered
        expect(screen.getByPlaceholderText(/Enter author name/i)).toBeInTheDocument();
        expect(screen.getByPlaceholderText(/Enter epigram text/i)).toBeInTheDocument();
    });
});

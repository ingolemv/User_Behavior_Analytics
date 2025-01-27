import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api';

export const getActivities = async () => {
    const response = await axios.get(`${API_BASE_URL}/activities`);
    return response.data;
};

export const getRules = async () => {
    const response = await axios.get(`${API_BASE_URL}/rules`);
    return response.data;
};

export const createRule = async (rule) => {
    const response = await axios.post(`${API_BASE_URL}/rules`, rule);
    return response.data;
};

export const deleteRule = async (id) => {
    await axios.delete(`${API_BASE_URL}/rules/${id}`);
};

export const getTrends = async () => {
    const response = await axios.get(`${API_BASE_URL}/trends`);
    return response.data;
}; 
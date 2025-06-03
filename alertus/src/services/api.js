import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const getAlertas = () => api.get('/alertas');
export const getEventos = () => api.get('/eventos');
export const postEvento = (data) => api.post('/eventos', data);
export const postRegiao = (data) => api.post('/regioes', data);

export default api;

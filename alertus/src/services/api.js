import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// Mock data helpers
const getMockData = (key, defaultValue = []) => {
  try {
    const data = localStorage.getItem(key);
    return data ? JSON.parse(data) : defaultValue;
  } catch {
    return defaultValue;
  }
};

const setMockData = (key, data) => {
  localStorage.setItem(key, JSON.stringify(data));
};

// Initialize mock data if not exists
const initializeMockData = () => {
  if (!localStorage.getItem('alertus_eventos')) {
    setMockData('alertus_eventos', [
      { id: 1, tipo: 'enchente', intensidade: 'Alta', data: '2024-05-01T10:00', regiao: 'Sul' },
      { id: 2, tipo: 'calor extremo', intensidade: 'Moderada', data: '2024-05-02T12:00', regiao: 'Nordeste' }
    ]);
  }
  
  if (!localStorage.getItem('alertus_regioes')) {
    setMockData('alertus_regioes', [
      { id: 1, nome: 'Sul', latitude: '-25.4284', longitude: '-49.2733' },
      { id: 2, nome: 'Nordeste', latitude: '-8.0476', longitude: '-34.8770' }
    ]);
  }
  
  if (!localStorage.getItem('alertus_alertas')) {
    setMockData('alertus_alertas', [
      { id: 1, mensagem: 'Chuvas intensas', nivel: 'Alto', regiao: 'Sul', data: '2024-05-01 10:00' },
      { id: 2, mensagem: 'Calor extremo', nivel: 'Medio', regiao: 'Nordeste', data: '2024-05-02 12:00' }
    ]);
  }
};

// Initialize on load
initializeMockData();

export const getAlertas = () => {
  return api.get('/alertas').catch(() => ({
    data: getMockData('alertus_alertas')
  }));
};

export const getEventos = () => {
  return api.get('/eventos').catch(() => ({
    data: getMockData('alertus_eventos')
  }));
};

export const postEvento = (data) => {
  return api.post('/eventos', data).catch(() => {
    const eventos = getMockData('alertus_eventos');
    const newEvento = {
      id: Date.now(),
      ...data
    };
    eventos.push(newEvento);
    setMockData('alertus_eventos', eventos);
    return { data: newEvento };
  });
};

export const postRegiao = (data) => {
  return api.post('/regioes', data).catch(() => {
    const regioes = getMockData('alertus_regioes');
    const newRegiao = {
      id: Date.now(),
      ...data
    };
    regioes.push(newRegiao);
    setMockData('alertus_regioes', regioes);
    return { data: newRegiao };
  });
};

export const getRegioes = () => {
  return api.get('/regioes').catch(() => ({
    data: getMockData('alertus_regioes')
  }));
};

export default api;

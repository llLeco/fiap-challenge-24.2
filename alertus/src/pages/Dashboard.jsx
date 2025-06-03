import React, { useEffect, useState } from 'react';
import AlertaCard from '../components/AlertaCard';
import { getAlertas } from '../services/api';

const Dashboard = () => {
  const [alertas, setAlertas] = useState([]);

  useEffect(() => {
    getAlertas()
      .then((res) => setAlertas(res.data))
      .catch(() => {
        // Dados mockados caso a API não responda
        setAlertas([
          {
            id: 1,
            mensagem: 'Chuvas intensas',
            nivel: 'Alto',
            regiao: 'Sul',
            data: '2024-05-01 10:00',
          },
          {
            id: 2,
            mensagem: 'Calor extremo',
            nivel: 'Medio',
            regiao: 'Nordeste',
            data: '2024-05-02 12:00',
          },
        ]);
      });
  }, []);

  return (
    <div>
      <h2>Alertas Ativos</h2>
      {alertas.map((a) => (
        <AlertaCard key={a.id} alerta={a} />
      ))}
    </div>
  );
};

export default Dashboard;

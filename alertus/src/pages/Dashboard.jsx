import React, { useEffect, useState } from 'react';
import AlertaCard from '../components/AlertaCard';
import { getAlertas, getEventos } from '../services/api';

const Dashboard = () => {
  const [alertas, setAlertas] = useState([]);
  const [eventos, setEventos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadData = async () => {
      try {
        const [alertasRes, eventosRes] = await Promise.all([
          getAlertas(),
          getEventos()
        ]);
        setAlertas(alertasRes.data);
        setEventos(eventosRes.data);
      } catch (error) {
        // Fallback se algo falhar
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
        setEventos([]);
      } finally {
        setLoading(false);
      }
    };
    
    loadData();
    
    // Atualizar dados a cada 30 segundos
    const interval = setInterval(loadData, 30000);
    return () => clearInterval(interval);
  }, []);

  if (loading) {
    return (
      <div className="text-center">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Carregando...</span>
        </div>
      </div>
    );
  }

  const alertasAtivos = alertas.filter(a => a.nivel === 'Alto' || a.nivel === 'Medio');
  const eventosRecentes = eventos.slice(-5).reverse(); // Últimos 5 eventos

  return (
    <div>
      <div className="row mb-4">
        <div className="col-12">
          <div className="d-flex justify-content-between align-items-center">
            <h2>Dashboard - Sistema Alertus</h2>
            <span className="badge bg-success">Sistema Online</span>
          </div>
          <p className="text-muted">Monitoramento em tempo real de eventos e alertas</p>
        </div>
      </div>

      {/* Cards de Estatísticas */}
      <div className="row mb-4">
        <div className="col-md-3">
          <div className="card text-white bg-danger">
            <div className="card-body">
              <h5 className="card-title">Alertas Ativos</h5>
              <h2 className="card-text">{alertas.length}</h2>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-warning">
            <div className="card-body">
              <h5 className="card-title">Alertas Críticos</h5>
              <h2 className="card-text">{alertas.filter(a => a.nivel === 'Alto').length}</h2>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-info">
            <div className="card-body">
              <h5 className="card-title">Eventos Hoje</h5>
              <h2 className="card-text">{eventos.filter(e => e.data.startsWith(new Date().toISOString().split('T')[0])).length}</h2>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-primary">
            <div className="card-body">
              <h5 className="card-title">Total Eventos</h5>
              <h2 className="card-text">{eventos.length}</h2>
            </div>
          </div>
        </div>
      </div>

      <div className="row">
        {/* Alertas Ativos */}
        <div className="col-md-8">
          <h3>Alertas Ativos ({alertas.length})</h3>
          {alertas.length === 0 ? (
            <div className="alert alert-success">
              <i className="bi bi-check-circle"></i> Nenhum alerta ativo no momento
            </div>
          ) : (
            alertas.map((a) => (
              <AlertaCard key={a.id} alerta={a} />
            ))
          )}
        </div>

        {/* Eventos Recentes */}
        <div className="col-md-4">
          <h3>Eventos Recentes</h3>
          {eventosRecentes.length === 0 ? (
            <div className="alert alert-info">Nenhum evento registrado</div>
          ) : (
            <div className="list-group">
              {eventosRecentes.map((evento) => (
                <div key={evento.id} className="list-group-item">
                  <div className="d-flex w-100 justify-content-between">
                    <h6 className="mb-1">
                      <span className="badge bg-primary me-2">{evento.tipo}</span>
                    </h6>
                    <small>{new Date(evento.data).toLocaleDateString('pt-BR')}</small>
                  </div>
                  <p className="mb-1">{evento.regiao}</p>
                  <small className={`badge ${
                    evento.intensidade === 'Baixa' ? 'bg-success' :
                    evento.intensidade === 'Moderada' ? 'bg-warning' :
                    evento.intensidade === 'Alta' ? 'bg-danger' : 'bg-dark'
                  }`}>
                    {evento.intensidade}
                  </small>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;

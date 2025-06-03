import React, { useEffect, useState } from 'react';
import { getEventos, postEvento } from '../services/api';

const Eventos = () => {
  const [eventos, setEventos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });
  const [form, setForm] = useState({
    tipo: '',
    intensidade: '',
    data: '',
    regiao: '',
  });

  const loadEventos = () => {
    getEventos()
      .then((res) => setEventos(res.data))
      .catch(() => setEventos([]));
  };

  useEffect(() => {
    loadEventos();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage({ type: '', text: '' });
    
    try {
      await postEvento(form);
      setMessage({ type: 'success', text: 'Evento cadastrado com sucesso!' });
      setForm({ tipo: '', intensidade: '', data: '', regiao: '' });
      loadEventos(); // Recarrega a lista
    } catch (error) {
      setMessage({ type: 'error', text: 'Erro ao cadastrar evento.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Cadastro de Evento</h2>
      
      {message.text && (
        <div className={`alert ${message.type === 'success' ? 'alert-success' : 'alert-danger'} alert-dismissible fade show`}>
          {message.text}
          <button type="button" className="btn-close" onClick={() => setMessage({ type: '', text: '' })}></button>
        </div>
      )}
      
      <form onSubmit={handleSubmit} className="mb-4">
        <div className="row g-3">
          <div className="col-md-3">
            <label className="form-label">Tipo</label>
            <select className="form-select" name="tipo" value={form.tipo} onChange={handleChange} required>
              <option value="">Selecione</option>
              <option value="enchente">Enchente</option>
              <option value="calor extremo">Calor Extremo</option>
              <option value="terremoto">Terremoto</option>
              <option value="vendaval">Vendaval</option>
              <option value="seca">Seca</option>
            </select>
          </div>
          <div className="col-md-3">
            <label className="form-label">Intensidade</label>
            <select className="form-select" name="intensidade" value={form.intensidade} onChange={handleChange} required>
              <option value="">Selecione</option>
              <option value="Baixa">Baixa</option>
              <option value="Moderada">Moderada</option>
              <option value="Alta">Alta</option>
              <option value="Extrema">Extrema</option>
            </select>
          </div>
          <div className="col-md-3">
            <label className="form-label">Data/Hora</label>
            <input
              type="datetime-local"
              className="form-control"
              name="data"
              value={form.data}
              onChange={handleChange}
              required
            />
          </div>
          <div className="col-md-3">
            <label className="form-label">Região</label>
            <input
              type="text"
              className="form-control"
              name="regiao"
              value={form.regiao}
              onChange={handleChange}
              placeholder="Ex: São Paulo"
              required
            />
          </div>
        </div>
        <button className="btn btn-success mt-3" type="submit" disabled={loading}>
          {loading ? (
            <>
              <span className="spinner-border spinner-border-sm me-2" role="status"></span>
              Salvando...
            </>
          ) : (
            'Salvar Evento'
          )}
        </button>
      </form>
      
      <h3>Eventos Registrados ({eventos.length})</h3>
      {eventos.length === 0 ? (
        <div className="alert alert-info">Nenhum evento cadastrado ainda.</div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Tipo</th>
                <th>Intensidade</th>
                <th>Data/Hora</th>
                <th>Região</th>
              </tr>
            </thead>
            <tbody>
              {eventos.map((e) => (
                <tr key={e.id}>
                  <td>
                    <span className="badge bg-primary">{e.tipo}</span>
                  </td>
                  <td>
                    <span className={`badge ${
                      e.intensidade === 'Baixa' ? 'bg-success' :
                      e.intensidade === 'Moderada' ? 'bg-warning' :
                      e.intensidade === 'Alta' ? 'bg-danger' : 'bg-dark'
                    }`}>
                      {e.intensidade}
                    </span>
                  </td>
                  <td>{new Date(e.data).toLocaleString('pt-BR')}</td>
                  <td>{e.regiao}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Eventos;

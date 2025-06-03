import React, { useEffect, useState } from 'react';
import { postRegiao, getRegioes } from '../services/api';

const Regioes = () => {
  const [regioes, setRegioes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });
  const [form, setForm] = useState({ nome: '', latitude: '', longitude: '' });

  const loadRegioes = () => {
    getRegioes()
      .then((res) => setRegioes(res.data))
      .catch(() => setRegioes([]));
  };

  useEffect(() => {
    loadRegioes();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage({ type: '', text: '' });
    
    try {
      await postRegiao(form);
      setMessage({ type: 'success', text: 'Região cadastrada com sucesso!' });
      setForm({ nome: '', latitude: '', longitude: '' });
      loadRegioes(); // Recarrega a lista
    } catch (error) {
      setMessage({ type: 'error', text: 'Erro ao cadastrar região.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Cadastro de Região</h2>
      
      {message.text && (
        <div className={`alert ${message.type === 'success' ? 'alert-success' : 'alert-danger'} alert-dismissible fade show`}>
          {message.text}
          <button type="button" className="btn-close" onClick={() => setMessage({ type: '', text: '' })}></button>
        </div>
      )}
      
      <form onSubmit={handleSubmit} className="mb-4">
        <div className="row g-3">
          <div className="col-md-4">
            <label className="form-label">Nome da Região</label>
            <input
              type="text"
              className="form-control"
              name="nome"
              value={form.nome}
              onChange={handleChange}
              placeholder="Ex: São Paulo"
              required
            />
          </div>
          <div className="col-md-4">
            <label className="form-label">Latitude</label>
            <input
              type="number"
              step="any"
              className="form-control"
              name="latitude"
              value={form.latitude}
              onChange={handleChange}
              placeholder="Ex: -23.5505"
              required
            />
          </div>
          <div className="col-md-4">
            <label className="form-label">Longitude</label>
            <input
              type="number"
              step="any"
              className="form-control"
              name="longitude"
              value={form.longitude}
              onChange={handleChange}
              placeholder="Ex: -46.6333"
              required
            />
          </div>
        </div>
        <button type="submit" className="btn btn-success mt-3" disabled={loading}>
          {loading ? (
            <>
              <span className="spinner-border spinner-border-sm me-2" role="status"></span>
              Salvando...
            </>
          ) : (
            'Salvar Região'
          )}
        </button>
      </form>

      <h3>Regiões Cadastradas ({regioes.length})</h3>
      {regioes.length === 0 ? (
        <div className="alert alert-info">Nenhuma região cadastrada ainda.</div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Coordenadas</th>
              </tr>
            </thead>
            <tbody>
              {regioes.map((regiao) => (
                <tr key={regiao.id}>
                  <td>
                    <strong>{regiao.nome}</strong>
                  </td>
                  <td>{regiao.latitude}</td>
                  <td>{regiao.longitude}</td>
                  <td>
                    <a 
                      href={`https://www.google.com/maps?q=${regiao.latitude},${regiao.longitude}`}
                      target="_blank"
                      rel="noopener noreferrer"
                      className="btn btn-sm btn-outline-primary"
                    >
                      Ver no Mapa
                    </a>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Regioes;

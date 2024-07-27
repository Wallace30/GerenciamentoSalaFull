import { useState, useEffect } from 'react';
import axios from 'axios';
import './ReservaApp.css';
const ReservaApp = () => {
  const [pessoas, setPessoas] = useState([]);
  const [salas, setSalas] = useState([]);
  const [idPessoa, setIdPessoa] = useState('');
  const [idSala, setIdSala] = useState('');
  const [dataInicio, setDataInicio] = useState('');
  const [dataFim, setDataFim] = useState('');
  const [reservas, SetReservas] = useState([]);
  const [modo, setModo] = useState('reserva'); // Novo estado para gerenciar o modo atual

  useEffect(() => {
    fetchPessoas();
    fetchSalas();
    fetchReservas();
  }, []);

  const fetchPessoas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/cadastropessoa');
      setPessoas(response.data);
    } catch (error) {
      console.error('Erro ao carregar pessoas:', error);
    }
  };

  const fetchSalas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/cadastrosala');
      setSalas(response.data);
    } catch (error) {
      console.error('Erro ao carregar salas:', error);
    }
  };

  const fetchReservas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/reserva');
      SetReservas(response.data);
    } catch (error) {
      console.error('Erro ao carregar as reservas:', error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const reservaData = {
      idPessoa,
      idSala,
      dataInicio,
      dataFim
    };

    try {
      await axios.post('http://localhost:8080/reserva', reservaData);
      alert('Reserva feita com sucesso!');
      fetchPessoas();
      fetchSalas();
      fetchReservas();
    } catch (error) {
      alert('Erro ao fazer reserva: ' + error.message);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/reserva/${id}`);
      alert('Reserva deletada com sucesso!');
      fetchReservas();
    } catch (error) {
      alert('Erro ao deletar reserva: ' + error.message);
    }
  };

  return (
    <div>
      <h1>Sistema de Reserva de Salas</h1>

      {/* Seletor de Modo */}
      <div>
        <button onClick={() => setModo('reserva')}>Fazer Reserva</button>
        <button onClick={() => setModo('deletar')}>Deletar Reserva</button>
      </div>

      {modo === 'reserva' && (
        <div>
          <h2>Fazer Reserva</h2>
          <form onSubmit={handleSubmit}>
            <label>
              Pessoa:
              <select value={idPessoa} onChange={(e) => setIdPessoa(e.target.value)} required>
                <option value="">Selecione uma pessoa</option>
                {pessoas.map((pessoa) => (
                  <option key={pessoa.id} value={pessoa.id}>{pessoa.nome}</option>
                ))}
              </select>
            </label>
            <br />
            <label>
              Sala:
              <select value={idSala} onChange={(e) => setIdSala(e.target.value)} required>
                <option value="">Selecione uma sala</option>
                {salas.map((sala) => (
                  <option key={sala.id} value={sala.id}>{sala.nome}</option>
                ))}
              </select>
            </label>
            <br />
            <label>
              Data de Início:
              <input type="datetime-local" value={dataInicio} onChange={(e) => setDataInicio(e.target.value)} required />
            </label>
            <br />
            <label>
              Data de Fim:
              <input type="datetime-local" value={dataFim} onChange={(e) => setDataFim(e.target.value)} required />
            </label>
            <br />
            <button type="submit">Fazer Reserva</button>
          </form>
        </div>
      )}

      {modo === 'deletar' && (
        <div>
          <h2>Nomes das Reservas Cadastradas:</h2>
          <ul>
            {reservas.map((reserva) => {
              const pessoa = pessoas.find(p => p.id === reserva.idPessoa);
              const sala = salas.find(s => s.id === reserva.idSala);

              return (
                <li key={reserva.id}>
                  Reserva ID: {reserva.id} | Pessoa: {pessoa ? pessoa.nome : 'Desconhecido'} | Sala: {sala ? sala.nome : 'Desconhecida'} | Data Início: {new Date(reserva.dataInicio).toLocaleString()} | Data Fim: {new Date(reserva.dataFim).toLocaleString()}
                  <button onClick={() => handleDelete(reserva.id)}>Deletar</button>
                </li>
              );
            })}
          </ul>
        </div>
      )}
    </div>
  );
};

export default ReservaApp;

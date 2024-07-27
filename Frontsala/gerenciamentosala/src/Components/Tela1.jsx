import { useState } from 'react';
import axios from 'axios';
import './CadastroSalaForm.css';
const CadastroSalaForm = () => {
    const [nome, setNome] = useState('');
    const [capacidade, setCapacidade] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/cadastrosala', {
                nome: nome,
                capacidade: parseInt(capacidade)
            });
            console.log(response.data);
            alert('Sala registrada com sucesso!');
            // Limpar os campos após o registro
            setNome('');
            setCapacidade('');
        } catch (error) {
            console.error('Erro ao registrar a sala:', error);
            alert('Erro ao registrar a sala');
        }
    };

    return (
        <div>
            <h2>Cadastro de Sala</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nome da Sala:</label>
                    <input
                        type="text"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Capacidade:</label>
                    <input
                        type="number"
                        value={capacidade}
                        onChange={(e) => setCapacidade(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Registrar Sala</button>
            </form>
        </div>
    );
};

export default CadastroSalaForm;

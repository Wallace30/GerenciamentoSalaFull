import { useState } from 'react';
import axios from 'axios';
import './CadastroPessoaForm.css';
const CadastroPessoaForm = () => {
    const [nome, setNome] = useState('');
    const [idade, setIdade] = useState('');
    const [cpf, setCpf] = useState('');


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/cadastropessoa', {
                nome: nome,
                idade: parseInt(idade),
                cpf: parseInt(cpf)
            });
            console.log(response.data);
            alert('Sala registrada com sucesso!');
            // Limpar os campos após o registro
            setNome('');
            setIdade('');
            setCpf;

        } catch (error) {
            console.error('Erro ao registrar o nome da pessoa', error);
            alert('Erro ao registrar a pessoa');
        }
    };

    return (
        <div>
            <h2>Cadastro de Pessoa</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nome da Pessoa:</label>
                    <input
                        type="text"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Idade:</label>
                    <input
                        type="number"
                        value={idade}
                        onChange={(e) => setIdade(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>CPF:</label>
                    <input
                        type="number"
                        value={cpf}
                        onChange={(e) => setCpf(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Registrar a Pessoa</button>
            </form>
        </div>
    );
};

export default CadastroPessoaForm;

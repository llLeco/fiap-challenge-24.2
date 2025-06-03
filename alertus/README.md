# 🚨 ALERTUS - Sistema de Alerta Antecipado

Sistema desenvolvido para o Trabalho da FIAP - Eventos Extremos da Natureza

## 🎯 Sobre o Projeto

O **Alertus** é um MVP de sistema de alerta antecipado para desastres naturais, desenvolvido em React.js com integração para backend Java. O sistema permite o cadastro de eventos, regiões e monitoramento de alertas em tempo real.

## ✨ Funcionalidades Implementadas

### 📱 **Dashboard**
- Monitoramento em tempo real de alertas ativos
- Cards estatísticos (alertas ativos, críticos, eventos do dia)
- Lista de eventos recentes
- Atualização automática a cada 30 segundos

### 🌪️ **Cadastro de Eventos**
- Formulário completo com validação
- Tipos: Enchente, Calor Extremo, Terremoto, Vendaval, Seca
- Níveis de intensidade: Baixa, Moderada, Alta, Extrema
- Feedback visual de sucesso/erro
- Lista de eventos com tabela responsiva

### 🗺️ **Cadastro de Regiões**
- Cadastro com nome, latitude e longitude
- Validação de coordenadas
- Integração com Google Maps
- Lista de regiões cadastradas

### 📊 **Histórico de Eventos**
- Filtros por tipo e data
- Tabela responsiva com badges coloridos
- Contador de resultados
- Função "limpar filtros"

### 🔐 **Login**
- Interface simples de autenticação
- Redirecionamento automático

## 🛠️ Tecnologias Utilizadas

- **React 18** - Biblioteca principal
- **React Router DOM** - Navegação entre páginas
- **Axios** - Consumo de APIs
- **Bootstrap 5** - Framework CSS
- **Webpack** - Bundler e dev server
- **Babel** - Transpilador JavaScript

## 🚀 Como Executar

### Pré-requisitos
- Node.js (versão 14 ou superior)
- npm

### Instalação e Execução
```bash
# 1. Navegar para a pasta do projeto
cd alertus

# 2. Instalar dependências
npm install

# 3. Iniciar o servidor de desenvolvimento
npm start

# 4. Acessar no navegador
# http://localhost:3000
```

## 💾 Sistema de Mock

O frontend possui um **sistema de mock completo** que funciona independente do backend:

- **localStorage** usado como "banco de dados" local
- **Fallback automático** se a API não responder
- **Dados iniciais** carregados automaticamente
- **Persistência** entre sessões do navegador

### Estrutura dos Dados Mock:
```javascript
// Eventos
alertus_eventos: [
  { id, tipo, intensidade, data, regiao }
]

// Regiões  
alertus_regioes: [
  { id, nome, latitude, longitude }
]

// Alertas
alertus_alertas: [
  { id, mensagem, nivel, regiao, data }
]
```

## 🔌 Integração com Backend

A aplicação está preparada para consumir APIs REST em `http://localhost:8080/api`:

- `GET /alertas` - Lista alertas ativos
- `GET /eventos` - Lista eventos
- `POST /eventos` - Cadastra novo evento
- `GET /regioes` - Lista regiões
- `POST /regioes` - Cadastra nova região

## 📱 Interface

### Páginas Disponíveis:
- **/** - Dashboard principal
- **/login** - Tela de login
- **/eventos** - Cadastro e listagem de eventos
- **/regioes** - Cadastro e listagem de regiões
- **/historico** - Histórico de eventos com filtros

### Características da UI:
- **Responsiva** - Funciona em desktop e mobile
- **Bootstrap 5** - Design moderno e consistente
- **Feedback visual** - Alerts, spinners, badges coloridos
- **Navegação intuitiva** - Navbar com todas as seções

## 🏗️ Estrutura do Projeto

```
alertus/
├── public/
│   └── index.html          # HTML base
├── src/
│   ├── components/         # Componentes reutilizáveis
│   │   ├── Navbar.jsx      # Barra de navegação
│   │   └── AlertaCard.jsx  # Card de alerta
│   ├── pages/              # Páginas da aplicação
│   │   ├── Dashboard.jsx   # Página principal
│   │   ├── Login.jsx       # Página de login
│   │   ├── Eventos.jsx     # Cadastro de eventos
│   │   ├── Regioes.jsx     # Cadastro de regiões
│   │   └── Historico.jsx   # Histórico de eventos
│   ├── services/           # Serviços de API
│   │   └── api.js          # Configuração axios + mocks
│   ├── App.jsx             # Componente principal
│   └── index.jsx           # Ponto de entrada
├── package.json            # Dependências
├── webpack.config.js       # Configuração Webpack
└── .babelrc               # Configuração Babel
```

## 🎨 Sistema de Cores

- **Verde** - Sucesso, baixo risco
- **Amarelo** - Atenção, risco moderado  
- **Vermelho** - Perigo, alto risco
- **Azul** - Informação, neutro
- **Cinza** - Secundário, inativo

## 📝 Status do Desenvolvimento

✅ **Completo e Funcional:**
- ✅ Todas as páginas implementadas
- ✅ Sistema de mock funcional
- ✅ Formulários com validação
- ✅ Feedback visual completo
- ✅ Design responsivo
- ✅ Integração preparada para backend
- ✅ Persistência de dados local

## 🤝 Equipe

- **Frontend:** Desenvolvido com foco em usabilidade e experiência do usuário
- **Backend:** Será integrado com APIs Java/JDBC
- **Banco:** PostgreSQL/MySQL com relacionamentos adequados

---

**Sistema pronto para demonstração e integração com backend Java!** 🚀


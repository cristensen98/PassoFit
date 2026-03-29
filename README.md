PassoFit 👟
PassoFit é um aplicativo Android nativo desenvolvido para ajudar usuários a monitorar sua atividade física diária de forma simples, privada e totalmente offline. O foco do aplicativo é a eficiência energética e a soberania dos dados do usuário.

Sobre o Projeto:

Diferente de outros contadores de passos, o PassoFit não exige conexão com a internet, não possui rastreadores de terceiros e não utiliza processamento em nuvem. Ele utiliza os sensores físicos do próprio smartphone para realizar a contagem, garantindo que suas informações de saúde nunca saiam do seu dispositivo.
Principais Características:
100% Offline: Funciona em qualquer lugar, sem necessidade de Wi-Fi ou dados móveis.
Baixo Consumo de Bateria: Design otimizado e gerenciamento inteligente do sensor acelerômetro.
Privacidade Total: Sem APIs de terceiros ou bibliotecas de Inteligência Artificial.
Interface Dark Mode: Visual moderno baseado em Material Design que economiza energia em telas AMOLED.

Funcionalidades:

Contador de Passos em Tempo Real: Monitoramento contínuo através do acelerômetro.
Cálculo de Calorias: Estimativa de queima calórica baseada na intensidade da caminhada.
Metas Diárias: Definição de objetivos personalizados (ex: 10.000 passos/dia).
Histórico Local: Banco de dados interno para consulta de desempenho de dias anteriores.
Persistência de Estado: O app lembra seus passos e metas mesmo após ser reiniciado.

Tecnologias e Metodologias:

O aplicativo foi construído utilizando as seguintes tecnologias nativas:
Linguagem: Java (Android SDK).
Interface: XML com ConstraintLayout para responsividade.
Banco de Dados: SQLite (via SQLiteOpenHelper) para o histórico de atividades.
Armazenamento Simples: SharedPreferences para configurações e metas.
Sensores: SensorManager e Sensor.TYPE_ACCELEROMETER.
Arquitetura: Padrão MVC (Model-View-Controller).
Lógica de Contagem
O app utiliza a Magnitude do Vetor de Aceleração para detectar passos:

Quando a variação da magnitude ultrapassa um limiar (threshold) específico, o sistema valida o movimento como um passo físico.

Como Executar o Projeto:

Pré-requisitos:
Android Studio instalado (versão Arctic Fox ou superior recomendada).
Dispositivo Android físico ou Emulador com suporte a acelerômetro.
Passo a Passo:
Clone este repositório ou baixe os arquivos fonte.
No Android Studio, vá em File > New > Import Project e selecione a pasta do PassoFit.
Certifique-se de que o Package Name está configurado como com.example.passofit.
Sincronize o projeto com os arquivos Gradle.
Clique no botão Run (ícone de play verde) para instalar no dispositivo.

Identidade Visual:

As cores do aplicativo foram escolhidas para refletir vitalidade e tecnologia:
Verde Esmeralda (#00C853): Representa saúde, progresso e o alcance de metas.
Cinza Profundo (#121212): Cor de fundo para conforto visual e economia de bateria.
Branco e Cinza Claro: Utilizados para legibilidade de textos e ícones secundários.

Licença:

Este projeto foi desenvolvido para fins educacionais como parte da disciplina de Desenvolvimento para Plataformas Móveis. Sinta-se à vontade para estudar e adaptar o código.
Desenvolvido por: 

CRISTENSEN UBIRATAN MOREIRA PORPINO
ELIAS DA COSTA SALES
JOSE FERNANDO ABREU MARTINS FILHO
JOAO GABRIEL SOARES MOURA
GUILHERME MENEZES VIEIRA
THIAGO VIDAL DE SOUSA

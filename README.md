# Desafio de Programação Java
Monitoramento de Sistema com Princípios SOLID

<div>
  <h2>Objetivos</h2>
  Desenvolver um aplicativo Java para monitoramento de recursos do sistema, aderindo aos princípios SOLID e utilizando a biblioteca Looca.
</div>

<div>
  <h2>Requisitos</h2>
  
  1. Biblioteca Looca: Utilize a biblioteca Looca para acessar dados do sistema.

  2. Abstrações e Interfaces: Empregue Classes Abstratas e Interfaces para estruturar o código.

  3. DAO (Data Access Object): Implemente o padrão DAO para a persistência de dados de cada entidade do sistema.
  
  4. Captura de Dados com TimerTask: Implemente rotinas para capturar dados periodicamente usando TimerTask.
  
  5. Tabela de Parametrização: Crie uma tabela para parametrizar os componentes do sistema, incluindo:
  
     - CPU: uso
  
     - Memória: uso
  
     - Disco: uso
  
   Ou seja o sistema sempre consultará essa tabela para saber o que deve ser lido;
  
  6. Uso de Mapper: Implemente a interface RowMapper para todas as consultas ao banco de dados.
  
  7. Nomenclatura: Escolha bons nomes para variáveis, parâmetros, métodos e classes.
  
  8. Princípio 'S' do SOLID: Assegure-se de que cada classe tenha uma única responsabilidade.
  
  9. Princípio 'I' do SOLID: Evite dependências desnecessárias entre classes.
  
  10. Organização de Pacotes: Separe os pacotes de acordo com o contexto, como database, dao, domínio, serviços, integração e etc...
  
  11. Utilização de Enumeradores: Utilize ao menos um enumerador no projeto para lidar com valores padrões;
</div>

<br>

>[!IMPORTANT]
> O desenvolvimento da aplicação foi realizado utilizando a versão 21.0.1 do Java, então fique atento à sua versão atual para evitar conflitos.

<br>

# Bibliotecas Utilizadas
Antes de continuar com a explicação da aplicação, devemos ressaltar algumas bibliotecas e API's externas utilizadas!

<div>
  <h2>API Looca</h2>
  <p>
    Desenvolvida pela equipe da São Paulo Tech School (SPTECH), a API Looca foi criada com o intuito de auxiliar os alunos
    na leitura de dados de máquinas através do Java.
  </p>
</div>

```java
        <dependency>
            <groupId>com.github.britooo</groupId>
            <artifactId>looca-api</artifactId>
            <version>2.2.0</version>
        </dependency>
  ```

<div>
  <h2>API Slack</h2>
  <p>
    API utilizada para o envio de alertas personalizados para a ferramenta de comunicação Slack.
  </p>
</div>

```java
        <dependency>
            <groupId>com.slack.api</groupId>
            <artifactId>slack-api-client</artifactId>
            <version>1.36.1</version>
        </dependency>
  ```

<div>
  <h2>JDBC e Mysql</h2>
  <p>
    Integração com banco de dados Mysql através da biblioteca JDBC.
  </p>
</div>

```java
        <dependency>
            <groupId>org.clojure</groupId>
            <artifactId>java.jdbc</artifactId>
            <version>0.7.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot</artifactId>
            <version>3.1.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.25.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
  ```

# Diretórios do Projeto

1. Alertas <br>
   Repositório destinado à verificação dos alertas, enviando uma mensagem personalizada para a ferramneta de gestão Slack no caso de alguma métrica ser atingida.
   As três métricas de alerta, da maior para a menor, são:
   - Emergência;
   - Perigo;
   - Prevenção.
   O valor a ser atingido por cada métrica é definido no banco de dados mysql no momento de sua criação.
2. Business <br>
   Repositório que armazena as classes referentes às tabelas do banco de dados.
3. Dao <br>
   Repositório responsável pelas classes de acesso ao banco, onde os devidos códigos de insert e select serão realizados.
4. Database <br>
   Local onde a conexão com o banco Mysql será realizada. Também é o local onde o script do banco se encontra.
5. Enumerators <br>
   Pasta responsável por armazenar todos os enumeradores utilizados no projeto.
6. Interfaces <br>
   Pasta responsável por armazenar todos as interfaces utilizadas no projeto.
7. Leitura <br>
   Diretório onde identificação da máquina e seus componentes lidos são identificados, sendo responsável também pelo TimerTask utilizado no agendamento das tarefas.
8. Looca <br>
   Diretório que abstrai algumas das diversas funções de monitoramento da API looca, incluindo:
   - Uso da CPU (%);
   - Frequência da CPU (Hertz);
   - Uso da Memória (%);
   - Uso do Disco (%);
   - Bytes Enviados na Rede;
   - Bytes Recebidos na Rede;
9. Rowmapper <br>
    Diretório onde os registros obtidos no banco de dados são transformados em objetos.
10. Utils <br>
    Diretório que contém as classes com métodos úteis para diversas outras classes da aplicação.

# Como Executar a Aplicação

1. Abra o script do banco "Desafio_Java.sql" localizado na pasta "database" e execute-o em algum programa a sua preferência (Workbench, terminal, etc);
2. Troque as credenciais de acesso ao banco na classe Conexao de acordo com as suas credenciais.
```java
  dataSource.setDriverClassName(dotenv.get("DRIVER_DB"));
  dataSource.setUrl(dotenv.get("URL_DB"));
  dataSource.setUsername(dotenv.get("USUARIO_DB"));   
  dataSource.setPassword(dotenv.get("SENHA_DB"));
```

>[!CAUTION]
>É altamente recomendado evitar o uso das credenciais do banco diretamente no código, pois pode ocasionar em uma possível falha de segurança.
>Dessa forma, crie um arquivo chamado ".env" diretamente no respositório clonado e coloque os seguintes itens, substituindo apenas o que estiver marcado entre {}:

```bash
  URL_DB="jdbc:mysql://localhost:3306/Desafio_Java"
  USUARIO_DB="{SEU_NOME_DE_USUARIO}"
  SENHA_DB="{SUA_SENHA}"
  DRIVER_DB="com.mysql.cj.jdbc.Driver"
```

3. Adicione a seguinte linha ao mesmo arquivo ".env" criado anteriormente:

```bash
  TOKEN_BOT_SLACK = "{SEU_TOKEN_AQUI}"
```

Caso não possua um token, veja aqui como criar um [Token Slack](https://documentation.botcity.dev/pt/plugins/slack/token/)

4. Na classe "Alertas" localizada na pasta "alertas", altere o que estiver entre {} no seguinte bloco de código:

```java
  ChatPostMessageRequest request = ChatPostMessageRequest.builder()
    .channel("{CODIGO_OU_NOME_DO_CHAT_PARA_ONDE_OS_ALERTAS_SERAO_ENVIADOS}")
    .text(":warning:  " + alerta)
    .build();
```

5. Após finalizar as alterações, execute a classe "Main" da aplicação na ferramente que desejar (IntelliJ, Netbeans, etc).
6. Selecione todos os componentes desejados para a sua maquina monitorar.
7. Aproveite sua nova aplicação de monitoramento!

>[!TIP]
>Caso queira verificar se a geração de alertas está devidamente funcional, modifique o seguinte bloco de código na classe "Alertas":

```java
  if (metricaEmergencia != 0 && metricaPerigo != 0 && metricaPrevencao != 0) {
      if (valor + 80 >= metricaEmergencia) {
          nivelAlerta += "Emergência ";
          alerta = true;
      } else if (valor + 80 >= metricaPerigo) {
          nivelAlerta += "Perigo ";
          alerta = true;
      } else if (valor + 80>= metricaPrevencao) {
          nivelAlerta += "Prevenção ";
          alerta = true;
      }
  }
```

# The End!

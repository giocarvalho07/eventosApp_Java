# eventosApp_Java
projeto que permite a criação de eventos {CRUD}, com relação de (One to Many) Evento - Participantes 

Projeto Eventos

<UML

{Evento}

long id (primary key);
string nome;
string local;
string data;
string horario;

{Convidado}

string rg (primary key);
string nomeConvidado;
string emailConvidado;

<DESENVOLVIMENTO

{Packages}
Model
Controller
Repository

{Class}
  -> Model
Evento
Convidado

  -> Controller
Index(raiz)
EventoController

{Interface}
 EventoRepository
 ConvidadoRepository
 
 {Templates}
 index (raiz)
 
  -> Evento
  detalheEvento.html
  formEvento.html
  mensagemValidacao.html
  
 {Static}
 Mterialize
  

Adicione alguma conexão com algum banco de dados SQL ou NoSQL
caso prefira adicione o H2 database

Apllication.properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.jpa.properties.hibrnate.format_sql=true
server.port=8090

Agora, vá até o browser e na url passe:
localhost:8090/h2

pront, seu banco de dados já está conectado

---------------------------------------------------------

Run As
    -> Spring Boot App

De um start na apliação e pronto !!

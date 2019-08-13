# Golden Raspberry Awards

### Para executar basta acessar a pasta raiz do projeto e digitar seguinte comando via terminal:

`mvn clean spring-boot:run`

Após iniciar o projeto é feita a inserção automaticamente do arquivo CSV.

O projeto disponibiliza solicitações via metodo GET através dos Links:

- http://localhost:8080/movies-list
Retorna um json com todos os dados do banco de dados que foram inseridos a partir do CSV 

- http://localhost:8080/producers-result
Obtém o produtor com maior intervalo entre dois prêmios, e o que obteve dois prêmios mais rápido.


### Para execução dos testes do projeto basta acessar a raiz do projeto e executar o seguinte comando terminal:
`mvn test`




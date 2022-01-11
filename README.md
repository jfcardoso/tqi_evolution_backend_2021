## Requisitos:
* Java 11
* Maven 3.6.3

### Etapas para testar a aplicação:

- Rodar a `./mvnw clean instal`.
- Iniciar o SpringBoot `./mvnw spring-boot:run`

### Criar Cliente:

```
curl --location --request POST 'http://localhost:8080/clientes' \
--header 'Content-Type: application/json' \
--data-raw '{
"senha": "123456",
"nome": "Jefferson Cardoso",
"email": "cliente@teste.com",
"cpf": "123.432.564-98",
"rg": "21.234.543-1",
"endereco": "Rua C, nº 2",
"bairro": "Centro",
"cidade": "São Paulo",
"estado": "SP",
"renda": 5450.00
}'
```
A resposta esperada é similar e esse Json:
```
{
    "id": 2,
    "nome": "Jefferson Cardoso",
    "email": "cliente@teste.com",
    "cpf": "123.432.564-98",
    "rg": "21.234.543-1",
    "endereco": "Rua C, nº 2",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "SP",
    "renda": 5450.00
}
```
Perceba-se que a senha não é retornada por questão de segurança.

Se olharem a implementação no ClienteController, irá notar que a senha foi guardada criptografada no banco.

### Buscar Cliente:

- Para buscar um cliente é necessário estar logado. Nessa aplicação optei por usar o HTTP BASIC. Segue abaixo a requisição:

```
curl --location --request GET 'http://localhost:8080/clientes' \
--header 'Authorization: Basic Y2xpZW50ZUB0ZXN0ZS5jb206MTIzNDU2'

```
A resposta espera é similar a esse Json:

```
{
    "id": 1,
    "nome": "Jefferson Cardoso",
    "email": "cliente@teste.com",
    "cpf": "123.432.564-98",
    "rg": "21.234.543-1",
    "endereco": "Rua C, nº 2",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "SP",
    "renda": 5450.00
}
```
### Criar um novo Empréstimo:

- Para criar um novo empréstimo é só chamar o endpoint abaixo (endpoint logado):

```
curl --location --request POST 'http://localhost:8080/emprestimos' \
--header 'Authorization: Basic Y2xpZW50ZUB0ZXN0ZS5jb206MTIzNDU2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "valor": 20000,
    "qntParcelas": 20,
    "dataPrimeiraParcela": "24-02-2022"
}'
```
A resposta esperada é esse Json:

```
{
    "id": 1,
    "valor": 20000,
    "qntParcelas": 20,
    "dataPrimeiraParcela": "24-02-2022",
    "emailCliente": "cliente@teste.com",
    "rendaCliente": 5450.00
}
```
O id é o código identificador do empréstimo.

#### Validação

Para validar a data da 1ª parcela, use o payload abaixo:

```
curl --location --request POST 'http://localhost:8080/emprestimos' \
--header 'Authorization: Basic Y2xpZW50ZUB0ZXN0ZS5jb206MTIzNDU2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "valor": 20000,
    "qntParcelas": 20,
    "dataPrimeiraParcela": "10-07-2022"
}'
```
A resposta esperada é esse Json:

```
{
    "mensagem": "A data da primeira parcela não pode exceder os três meses."
}
```

### Listar empréstimos:

Para listar os empréstimos do cliente basta chamar o endpoint abaixo:

```
curl --location --request GET 'http://localhost:8080/emprestimos' \
--header 'Authorization: Basic Y2xpZW50ZUB0ZXN0ZS5jb206MTIzNDU2'

```
O resultado esperado é como o observado no Json abaixo:

```
[
    {
        "id": 1,
        "valor": 20000.00,
        "qntParcelas": 20
    }
]
```
### Listar detalhes de um empréstimo:

- Para listar o detalhe de um empréstimo é necessário passar o código(id) do empréstimo
na requisição.

```
curl --location --request GET 'http://localhost:8080/emprestimos/1' \
--header 'Authorization: Basic Y2xpZW50ZUB0ZXN0ZS5jb206MTIzNDU2'

```
O resultado esperado é como o observado no Json abaixo:

```
{
    "id": 1,
    "valor": 20000.00,
    "qntParcelas": 20,
    "dataPrimeiraParcela": "24-02-2022",
    "emailCliente": "cliente@teste.com",
    "rendaCliente": 5450.00
}
```


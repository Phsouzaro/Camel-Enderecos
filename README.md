# Aplicação de Consulta de Endereço via CEP com Apache Camel

Esta aplicação é uma API RESTful que permite consultar endereços no Brasil através de um CEP fornecido pelo usuário. A aplicação utiliza a API pública do ViaCEP para buscar os dados do endereço correspondente ao CEP informado.

## Funcionalidades

- **Consulta de Endereço**: A aplicação aceita requisições HTTP GET com um CEP como parâmetro e retorna um objeto JSON contendo o endereço correspondente.
- **Tratamento de Erros**: Caso o CEP informado não seja encontrado na base de dados do ViaCEP, a aplicação retorna um objeto de erro detalhando o problema.

## Endereço de Retorno

A requisição retorna um objeto JSON no seguinte formato:

```json
{
 "cep": "string",
 "logradouro": "string",
 "complemento": "string",
 "bairro": "string",
 "localidade": "string",
 "uf": "string",
 "errorResponse": {
    "code": "integer",
    "message": "string",
    "cause": "string"
 }
}
```

### Campos

- **cep**: O CEP informado na requisição.
- **logradouro**: O nome do logradouro correspondente ao CEP.
- **complemento**: Informações adicionais sobre o endereço, como números ou nomes de ruas.
- **bairro**: O nome do bairro onde o endereço está localizado.
- **localidade**: O nome da cidade ou município onde o endereço está localizado.
- **uf**: A sigla do estado onde o endereço está localizado.
- **errorResponse**: Objeto que contém detalhes do erro caso a consulta não seja bem-sucedida.

### Objeto de Erro

Caso ocorra um erro na consulta, o objeto `errorResponse` será preenchido com os seguintes campos:

- **code**: O código de status HTTP da resposta.
- **message**: A mensagem de erro descritiva.
- **cause**: A causa específica do erro, se disponível.

## Rota Principal do Camel

A aplicação utiliza o Apache Camel para definir a rota de processamento das requisições. A rota principal é configurada para:

1. **Processar a requisição**: Utiliza o `BuscarEnderecoProcessor` para preparar a requisição para a API do ViaCEP.
2. **Marshall e Unmarshall**: Converte os dados para JSON para a requisição e de JSON para o objeto de domínio `EnderecoDomain` para a resposta.
3. **Tratamento de Erros**: Caso ocorra uma exceção durante a requisição, a rota direciona para o `HandleErrorProcessor`, que cria um objeto de erro detalhado.

## Exemplo de Uso

Para consultar um endereço, faça uma requisição GET para a rota `/cep/{cep}`, substituindo `{cep}` pelo CEP desejado. Por exemplo:

```
GET /cep/12345678
```

### Resposta de Sucesso

```json
{
 "cep": "12345678",
 "logradouro": "Rua Exemplo",
 "complemento": "S/N",
 "bairro": "Bairro Exemplo",
 "localidade": "Cidade Exemplo",
 "uf": "SP",
 "errorResponse": null
}
```

### Resposta de Erro

```json
{
 "cep": "12345678",
 "logradouro": null,
 "complemento": null,
 "bairro": null,
 "localidade": null,
 "uf": null,
 "errorResponse": {
    "code": 404,
    "message": "CEP não encontrado",
    "cause": "CEP 12345678 não encontrado na base de dados do ViaCEP"
 }
}
```

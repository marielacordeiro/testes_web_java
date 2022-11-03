# Descrição dos casos de teste


### Teste de Sistema
Basicamente tivemos a ideia de testar alguns Bad Requests, visando eles serem específicos para a falta de alguns dos parâmetros requisitados, mostrando assim seu resultado como 400 e tendo suas áreas de erros, possuindo assim suas respectivas mensagens. Foram criados dois métodos de teste englobando essa situação: uma para o jurosComposto e outro para o jurosSimples. 

<br />

> #### Casos de teste
>
>> - Entrada: Falta do parametro taxa
>> - Resultado: 400 e Required request parameter 'taxa' for method parameter type Double is not present
>
>> - Entrada: Falta do parametro parcelas
>> - Resultado: 400 e Required request parameter 'parcelas' for method parameter type Integer is not present
>
>> - Entrada: Falta do parametro valor
>> - Resultado: 400 e Required request parameter 'valor' for method parameter type Double is not present

Depois foi testado a funcionalidade padrão da aplicação, para ver que estava tudo certo, foi feito uma série de testes sobre isso.

<br />

> #### Casos de teste --> JurosSimples
>
>> Entradas: true, false, 120, 0.02, 6, 148.8, 24.8
>> Resultado: 200 e true, false, 120, 0.02, 6, 148.8, 24.8
>
>> Entradas: true, false, 339000, 0.50, 2, 705120.0, 352560.0
>> Resultado: 200 e true, false, 339000, 0.50, 2, 705120.0, 352560.0
>
>> Entradas: true, false, 0.88, 0.12, 1, 1.0472, 1.0472
>> Resultado: 200 e true, false, 0.88, 0.12, 1, 1.0472, 1.0472
>
>> Entradas: true, false, 5600, 0.5, 17, 54488.0, 3205.176470588235
>> Resultado: 200 e true, false, 5600, 0.5, 17, 54488.0, 3205.176470588235
>
>> Entradas: true, false, 0, 0.04, 1, 0, 0
>> Resultado: 200 e true, false, 0, 0.04, 1, 0, 0
>
>> Entradas: true, false, 10, 2, 2, 50.8, 25.4
>> Resultado: 200 e true, true, false, 10, 2, 2, 50.8, 25.4

<br />

> #### Casos de teste --> JurosComposto
>
>> Entradas: true, true, 120, 0.02, 6, 150.48627558348, 25.08104593058
>> Resultado: 200 e true, true, 120, 0.02, 6, 150.48627558348, 25.08104593058
>
>> Entradas: true, true, 339000, 0.50, 2, 793293.9, 396646.95
>> Resultado: 200 e true, true, 339000, 0.50, 2, 793293.9, 396646.95
>
>> Entradas: true, true, 0.88, 0.12, 1, 1.0472, 1.0472
>> Resultado: 200 e true, true, 0.88, 0.12, 1, 1.0472, 1.0472
>
>> Entradas: true, true, 5600, 0.5, 17, 6177599.554130504, 363388.20906650025
>> Resultado: 200 e true, true, 5600, 0.5, 17, 6177599.554130504, 363388.20906650025
>
>> Entradas: true, false, 0, 0.04, 1, 0, 0
>> Resultado: 200 e true, false, 0, 0.04, 1, 0, 0
>
>> Entradas: true, true, 10, 2, 2, 91.20099999999998, 45.60049999999999
>> Resultado: 200 e true, true, 10, 2, 2, 91.20099999999998, 45.60049999999999

### Testes Unitários da classe Calcula Juros

Sendo uma classe simples de testar, apenas testamos os diferentes comportamentos possíveis de cada método de calcular juros para simular os resultados esperados de cada um quando há seguro e quando não há.

> #### Casos de teste - Juros Simples
>
>> Entradas: 5.00, 0.05, 3, com seguro
>> 
>> Resultado: 0.9000000000000001
>
>> Entradas: 5.00, 0.05, 3, sem seguro
>> 
>> Resultado: 0.75

> #### Casos de teste - Juros Compostos
>
>> Entradas: 5.00, 0.05, 3, com seguro
>>
>> Resultado: 0.9550799999999997
>
>> Entradas: 5.00, 0.05, 3, sem seguro
>>
>> Resultado: 0.788125

# Descrição dos casos de teste


### Teste da Classe Emprestimo
Basicamente tivemos que testar a classe emprestimo que dependia da classe CalculoDeJuros, então criamos um Mock para fazer um duble da classe calculo de juros e poder efetuar as operações da mesma como desejassemos.

<br />

> #### Casos de teste --> Custo Total Juros Simples
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 4660.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 8563.14
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 287118.06

<br />

> #### Casos de teste --> Custo Total Juros Simples Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 4560.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 8258.04
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 275278.14

<br />

> #### Casos de teste --> Custo Total Juros Compostos
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 21706.569678409818
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 35614.12421491493
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 429111.4065172286

<br />

> #### Casos de teste --> Custo Total Juros Compostos Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 20166.55586861808
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 31459.921882708655
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 392751.23035234027

<br />

> #### Casos de teste --> Valor Parcela Juros Simples
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 466.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 570.876
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 35889.7575

<br />

> #### Casos de teste --> Valor Parcela Juros Simples Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 456.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 550.5360000000001
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 34409.7675

<br />

> #### Casos de teste --> Valor Parcela Juros Compostos
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 2170.656967840982
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 2374.2749476609956
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 43743.65317255382

<br />

> #### Casos de teste --> Valor Parcela Juros Compostos Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 2016.6555868618082
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 2097.3281255139104
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 40766.11753639878

### Teste de Integração
Basicamente tivemos que testar a classe emprestimo que dependia da classe CalculoDeJuros, porém desta vez utilizamos as classes reais ao invés de mocks para que elas sejam testadas em conjunto e ver se tudo funciona como o esperado

<br />

> #### Casos de teste --> Custo Total Juros Simples
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 4660.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 8563.14
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 287118.06

<br />

> #### Casos de teste --> Custo Total Juros Simples Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 4560.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 8258.04
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 275278.14

<br />

> #### Casos de teste --> Custo Total Juros Compostos
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 21706.569678409818
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 35614.12421491493
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 429111.4065172286

<br />

> #### Casos de teste --> Custo Total Juros Compostos Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 20166.55586861808
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 31459.921882708655
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 392751.23035234027

<br />

> #### Casos de teste --> Valor Parcela Juros Simples
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 466.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 570.876
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 35889.7575

<br />

> #### Casos de teste --> Valor Parcela Juros Simples Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 456.0
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 550.5360000000001
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 34409.7675

<br />

> #### Casos de teste --> Valor Parcela Juros Compostos
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 2170.656967840982
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 2374.2749476609956
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 43743.65317255382

<br />

> #### Casos de teste --> Valor Parcela Juros Compostos Sem Seguro
>
>> Entradas: 1000, 0.35, 10
>> Resultado: 2016.6555868618082
>
>> Entradas: 2034, 0.2, 15
>> Resultado: 2097.3281255139104
>
>> Entradas: 147999, 0.1, 8
>> Resultado: 40766.11753639878
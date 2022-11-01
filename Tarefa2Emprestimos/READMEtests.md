# Descrição dos casos de teste


### Teste de integração
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

Depois foi testado a funcionalidade padrão da aplicação, para ver que estava tudo certo, foi feito uma série de testes sobre isso. OBS: Tal caso de teste seria usado no método de jurosSimples, para conseguir os casos do jurosComposta basta modificar o segundo parâmetro para true.

<br />

> #### Casos de teste
>
>> - Entradas: true, false, 120, 0.02, 6, 148.8, 24.8
>> - Resultado: 200 e true, false, 120, 0.02, 6, 148.8, 24.8
>
>> - Entradas: true, false, 339000, 0.50, 2, 705120.0, 352560.0
>> - Resultado: 200 e true, false, 339000, 0.50, 2, 705120.0, 352560.0
>
>> - Entradas: true, false, 0.88, 0.12, 1, 1.0472, 1.0472
>> - Resultado: 200 e true, false, 0.88, 0.12, 1, 1.0472, 1.0472
>
>> - Entradas: true, false, 5600, 0.5, 17, 54488.0, 3205.176470588235
>> - Resultado: 200 e true, false, 5600, 0.5, 17, 54488.0, 3205.176470588235
>
>> - Entradas: true, false, 0, 0.04, 1, 0, 0
>> - Resultado: 200 e true, false, 0, 0.04, 1, 0, 0
>
>> - Entradas: true, false, 10, 2, 2, 50.8, 25.4
>> - Resultado: 200 e true, true, false, 10, 2, 2, 50.8, 25.4

### Outro teste




### Outro teste
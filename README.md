# Planets-api





```
POST: http://localhost:8080/add
BODY:

{
 "name":"Terra",
 "weather":"quente"
}

HTTP-RESPONSE = 200 caso a execucao seja feita com sucesso.
```


```
GET :http://localhost:8080/

[
    {
        "id": "5cbe6b5d510d9f6e4c9ccbbe",
        "name": "Terra",
        "weather": "quente",
        "quantityMovies": 0
    }
]

```


```
GET :http://localhost:8080/api

[
    {
        "id": 1,
        "name": "Tatooine",
        "quantity": 10
    },
    {
        "id": 2,
        "name": "Alderaan",
        "quantity": 4
    },
    {
        "id": 3,
        "name": "Yavin IV",
        "quantity": 2
    },
    {
        "id": 4,
        "name": "Hoth",
        "quantity": 2
    },
    {
        "id": 5,
        "name": "Dagobah",
        "quantity": 6
    },
    {
        "id": 6,
        "name": "Bespin",
        "quantity": 2
    },
    {
        "id": 7,
        "name": "Endor",
        "quantity": 2
    },
    {
        "id": 8,
        "name": "Naboo",
        "quantity": 8
    },
    {
        "id": 9,
        "name": "Coruscant",
        "quantity": 8
    },
    {
        "id": 10,
        "name": "Kamino",
        "quantity": 2
    },
    {
        "id": 11,
        "name": "Geonosis",
        "quantity": 2
    },
    {
        "id": 12,
        "name": "Utapau",
        "quantity": 2
    },
    {
        "id": 13,
        "name": "Mustafar",
        "quantity": 2
    },
    {
        "id": 14,
        "name": "Kashyyyk",
        "quantity": 2
    },
    {
        "id": 15,
        "name": "Polis Massa",
        "quantity": 2
    },
    {
        "id": 16,
        "name": "Mygeeto",
        "quantity": 2
    },
    {
        "id": 17,
        "name": "Felucia",
        "quantity": 2
    },
    {
        "id": 18,
        "name": "Cato Neimoidia",
        "quantity": 2
    },
    {
        "id": 19,
        "name": "Saleucami",
        "quantity": 2
    },
    {
        "id": 27,
        "name": "Ord Mantell",
        "quantity": 2
    },
    {
        "id": 61,
        "name": "Jakku",
        "quantity": 2
    }
]

```

```
GET: http://localhost:8080/{id}
BODY:

{
 "name":"Terra",
 "weather":"quente"
}

```

```
DELETE: http://localhost:8080/{id}
BODY:

{
 "name":"Terra",
 "weather":"quente"
}

```


```
GET: http://localhost:8080/name/{name}
BODY:

{
 "name":"Terra",
 "weather":"quente"
}

```

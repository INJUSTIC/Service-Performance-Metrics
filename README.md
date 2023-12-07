# Service-Performance-Metrics

## Używane technologii
Java, Spring Boot, REST API, MeterRegistry

## Overview
To jest aplikacja napisana w Spring Boot. Zadaniem tego projektu jest tworzenie losowych jsonów o podanym rozmiarze, generowanie danych w formacie CSV, przeprowadzenie operacji matematycznych na kolumnach oraz raportowanie użycia zasobów komputera każdym z serwisów.

## Kontrolery:
### DataGenerationController
- GET /generate/json/{size} - zwraca listę zgenerowanych jsonów z losowymi danymi o podanym rozmiarze

### DataProcessingController
- GET /data/csv/{size} - pobiera dane z 1 serwisu oraz zwraca dane w formacie CSV z kolumnami ‘type, _id, name, type, latitude, longitude’
- GET /data/customCsv/{size} - pobiera dane z 1 serwisu oraz zwraca dane w formacie CSV z podanymi kolumnami (kolumny podawać poprzez parameter ?columns=kolumna1,kolumna2,...). Np zapytanie na adres "http://localhost:8080/data/customCsv/5?columns=_id,name,fullName" zwróci dane w formacie CSV z kolumnami _id,name,fullName
- GET /calculate/{size} - pobiera dane z 1, wykonuje proste operacje matematyczne podane poprzez parameter operations (?operations=operacja1,operacja2,...) i zwraca wynik tych operacji wraz z operandami. Np zapytanie na adres "http://localhost:8080/calculate/100?operations=latitude*longitude,sqrt(distance)" zwróci dane w formacie CSV z kolumnami latitude;longitude;distance;latitude*longitude;sqrt(distance)
#### Ważne
Dla wykorzystywania operatorów '+' oraz '^' zamiast pisania tych symboli w pasku adresu trzeba napisać '%2B' '%5E' odpowiednio, bo te znaki są zarezerwowane przez przeglądarkę i nie można ich używać

### ReportController
- GET /statistics - zwraca raport, która zawiera informacje takie jak użycie pamięci w czasie dla każdego z poprzednich serwisów oraz czas zapytań http pomiędzy serwisami 3->2->1. Raport na 1k,10k,100k wygenerowanych jsonow.

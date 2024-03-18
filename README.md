# postcode-api

## Requirements / Purpose

* Consolidate my Java back-end knowledge
* Consolidate Controller & Service layer testing in Java
* Ensure that information is securely handled

## MVP

* Mobile clients can retrieve suburb info by postcode and vice versa
* A secured API that allows clients to add new suburb and post code combinations
* A form of persistence (Database)
* Testing for Controller/Service layers

## Build Steps

## Design Goals / Approach

## Features

## Known issues

## Future Goals

## Struggles

## LOG

### 14th March 2024

* My first thought is that I'll be using hash maps to pair up the suburb names with the postcodes
* I'm iffy on Java testing so this will be a focus for me
* Going to initalise my repo and Java file. I don't think any unique packages (ones that I haven't used before) will be needed.
* Need to plan out the data table/entity

* So I toyed with the idea of using a separate entities and connecting them with a 1-1 relationship but this seems unneccesary. Hashmaps can achieve this functionality and seem to be safe. And also it's not like I'm going to be repeating the same postcodes or suburbs.

#### ACHIEVED:
* Initalised the project file with: Spring Boot DevTools, Spring Web & MySQL Driver
* Initalised git repo and started this readme
* I've decided to use hashmaps to store my data

#### TOMORROW
* Start fleshing out my API
* Research into my implementation

### 15th March 2024

* added Entity and new dependencies (spring jpa and model mapper)

### 18th March 2024

* added Service, Controller, ModelMapping Config, Global Error Handler initialised, Repo
* Working through this has made me realise that using hashmaps is not a good idea. This is because it makes more sense to have each value (suburb and postcode) in the same table with their own Columns. I feel a bit silly that I didn't realise this straight away. I muddled the concept of different tables with one-to-one relationships and the relationships between Columns in the same table. Additionally, accessing hashmap keys from their values doesn't seem to be best practice even if the key & values are unique.
* Silly mistake but I'm glad I realised this before fully implementing anything. I have affirmed a clearer understanding on relationships within and between tables.


# Coworking Space Digitalisierungsprototyp

##Ausgangslage

Ein Coworking Space in der Agglomeration von Zürich möchte in Zukunft seine Mitglieder und die Nutzung des Angebots digital über eine Webapplikation erfassen. Dazu sollte zuerst ein minimaler Prototyp realisiert werden, um den Kunden besser abholen zu können.

## Projektbeschreibung

Dieses Repository enthält einen Prototypen für die digitale Erfassung der Coworking Space-Nutzung. Die Anwendung besteht aus Server- und Client-Applikationen, die über eine HTTP-API kommunizieren. Der Prototyp unterstützt die Rollen Administrator, Mitglied und Besucher.

## Erste Schritte

1. Erstelle eine Kopie (fork) von diesem Projekt.
1. Stelle sicher, dass Docker installiert ist und läuft.
1. Stelle sicher, dass Visual Studio Code und die Erweiterung Remote Container installiert ist.
1. Klone (clone) das Projekt lokal, um damit arbeiten zu können.
1. Öffne das Projekt mit Visual Studio Code.
1. Öffne das Projekt im Entwicklungscontainer.
1. Starte das Projekt mit dem Kommando `Quarkus: Debug current Quarkus Project`
1. Probiere die Client-Applikation unter http://localhost:8080 aus.
1. Schaue die API auf http://localhost:8080/q/swagger-ui/ an.

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

##Testdaten

Die Testdaten befinden sich unter /workspace/src/main/java/ch/zli/m223/service/TestDataService.java die werden bei der Ausführung des Projekts gestartet (./mvnw quarkus:dev) Oder 
View>Command Palette> Quarkus: Debug current Quarkus project.

Man kann auch seine eigene Test Daten angeben wichtig ist einfach, dass diesen Tag @IfBuildProfile("dev") vorhanden ist. Ebenfalls darf man nicht bei dem Kontruktor von der Klasse
@Observes StartupEvent event vergessen. 



### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`


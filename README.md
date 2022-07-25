# LPSolutionsTask

## Description

This is a simple application which displays a given html template built via a Fluent API, and fills in it's variables using request/url params.

## Dependencies

This SpringBoot application was built using Java 18, the used dependencies can be found in the [pom.xml](pom.xml) file.

## Endpoint examples

**Raw**

http://localhost:8080/template/show


**With query params**

http://localhost:8080/template/show?email=your@mail.com&name=YourName&repository_url=https://github.com

## Further enhancement options

The app currently only contains a very simple some test to see if the TemplateController exists, and the application is able to start.
Playwright (e2e) & unit tests could be added in the future to verify the generated html file.
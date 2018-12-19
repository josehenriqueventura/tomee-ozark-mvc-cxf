# Java EE 8 MVC (JSR-371) with Apache CXF
This is a basic example on how to run Java EE 8 MVC (JSR-371) with Apache CXF

### Run the test

``` 
mvc clean test
```

There is only a test which verifies if the content passed from
`HomeController.java` to the model `javax.mvc.Models` will be rendered by `home.jsp`.

## Run the app

```
 mvn clean install -DskipTests=true tomee:run
```

and then run the curl command below or access link through a WebBrowser.

```
curl http://localhost:8080/mvc-cxf/app/home
```

Result

```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MVC 1.0 DEMO</title>
</head>
<body>
    <h1 id="printId">Hello World!!!</h1>
</body>
</html>
```
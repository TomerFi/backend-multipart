package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
    var backend = "http://crda-backend-dev-crda.apps.sssc-cl01.appeng.rhecoeng.com";
//    var backend = "http://localhost:8080";
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(URI.create(String.format("%s/api/v3/dependency-analysis/maven", backend)))
      .setHeader("Accept", "multipart/mixed")
      .setHeader("Content-Type", "text/vnd.graphviz")
      .POST(HttpRequest.BodyPublishers.ofByteArray(Files.readAllBytes(Paths.get(App.class.getClassLoader().getResource("sut_dot_graph").toURI()))))
      .build();

    var response = client.send(request, HttpResponse.BodyHandlers.ofByteArray()); // THIS THROWS ERROR
    System.out.println(new String(response.body()));
  }
}

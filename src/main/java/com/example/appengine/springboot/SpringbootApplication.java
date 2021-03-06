/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.springboot;

// [START gae_java11_helloworld]
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
@RestController
public class SpringbootApplication {
  private static final Logger slf4jLogger = LoggerFactory.getLogger(SpringApplication.class);
  private static final java.util.logging.Logger juLogger = java.util.logging.Logger.getLogger(SpringApplication.class.getName());

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }

  @GetMapping("/*")
  public void hello(HttpServletRequest request) throws IOException {
    String uuid = UUID.randomUUID().toString();

    log(String.format("['%s'] START REQUEST", uuid));
    log(String.format("['%s'] Path Info : '%s'", uuid, request.getPathInfo()));
    log(String.format("['%s'] URI : '%s'", uuid, request.getRequestURI()));
    log(String.format("['%s'] URL : '%s'", uuid, request.getRequestURL()));

    request.getHeaderNames().asIterator().forEachRemaining(
            hName -> request.getHeaders(hName).asIterator().forEachRemaining(
                    header -> log(String.format("['%s'] Header '%s' : '%s'", uuid, hName, header))
            )
    );

    request.getParameterMap().entrySet().forEach(
            e -> log(String.format("['%s'] Parameter '%s' : '%s'", uuid, e.getKey(), Arrays.toString(e.getValue())))
    );

    String body = IOUtils.toString(request.getReader());
    log(String.format("['%s'] Body : '%s'", uuid, body));
    log(String.format("['%s'] END REQUEST", uuid));
  }

  private void log(String message) {
//    System.out.println("stdout : " + message);
//
    slf4jLogger.info(message);
//    slf4jLogger.info("Slf4j : " + message);
//    slf4jLogger.error("Slf4j : " + message);
//
//    juLogger.info("Jul : " + message);
//    juLogger.severe("Jul : " + message);
  }

}
// [END gae_java11_helloworld]

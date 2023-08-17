/*
 * Copyright © 2023 Institut fuer Kommunikationstechnik - FH-Dortmund (codebase.ikt@fh-dortmund.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ict.model.jsonld.Context;

import java.util.Arrays;
import org.ict.gson.GsonUtils;
import org.ict.model.jsonld.context.Context;
import com.google.gson.Gson;

public class Context_T {

  public static void main(String[] args) {
    Gson gson = new Gson().newBuilder()
        .registerTypeAdapter(Context[].class, GsonUtils.getContextSerializer())
        .registerTypeAdapter(Context[].class, GsonUtils.getContextDeserializer()).create();

    Context[] contexts = new Context[] {
        Context.builder().prefix(null).namespace("https://w3id.org/bot#").build(), Context.builder()
            .prefix("rdf").namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#").build(),
        Context.builder().prefix("iot").namespace("http://iotschema.org/").build()};

    String json = gson.toJson(contexts);
    System.out.println(json);
    Context[] newContexts = gson.fromJson(json, Context[].class);
    System.out.println(Arrays.toString(newContexts));
  }
}

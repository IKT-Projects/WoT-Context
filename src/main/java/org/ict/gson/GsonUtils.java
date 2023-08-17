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
package org.ict.gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.ict.model.jsonld.context.Context;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

public class GsonUtils {
  public static JsonSerializer<Context[]> getContextSerializer() {
    return (src, typeOfSrc, context) -> {
      JsonArray array = new JsonArray();
      JsonObject ele = new JsonObject();
      for (Context c : src) {
        if (c.getPrefix() == null) {
          array.add(c.getNamespace());
        } else {
          ele.addProperty(c.getPrefix(), c.getNamespace());
        }
      }
      array.add(ele);
      return array;
    };
  }

  public static JsonDeserializer<Context[]> getContextDeserializer() {
    return (json, typeOfT, context) -> {
      JsonArray array = json.getAsJsonArray();
      List<Context> contexts = new ArrayList<>();

      for (JsonElement jsonElement : array) {
        if (jsonElement.isJsonObject()) {
          JsonObject o = jsonElement.getAsJsonObject();
          Set<String> keys = o.keySet();
          for (String key : keys) {
            contexts.add(Context.builder().prefix(key).namespace(o.get(key).getAsString()).build());
          }
        } else if (jsonElement.isJsonPrimitive()) {
          contexts.add(Context.builder().prefix(null).namespace(jsonElement.getAsString()).build());
        }
      }
      return contexts.toArray(new Context[0]);
    };
  }
}

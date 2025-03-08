package io.github.rodolfobortolozo;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscaCep {

    public Endereco pesquisar(String cep) {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                resposta.append(linha);
            }
            br.close();
            conn.disconnect();

            Gson gson = new Gson();
            return gson.fromJson(resposta.toString(), Endereco.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

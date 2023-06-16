package jogo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ranking {
    public Container painel;
    private String caminhoArquivo = "src/jogo/ranking.json";
    private Estilo estilo=new Estilo();

    public Ranking(String nome, int pontuacao, int dificuldade) {// Salva a pontuação do jogador
        try {
            // Carrega o conteúdo do arquivo JSON
            String json = new String(Files.readAllBytes(Paths.get(caminhoArquivo)));

            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            JsonArray jsonArray = gson.fromJson(json, JsonArray.class);//carrega o conteudo para um jsonarray

            jsonObject.addProperty("Jogador", nome);//constroi o objeto
            jsonObject.addProperty("Pontuacao", pontuacao);
            jsonObject.addProperty("Dificuldade", dificuldade);
            jsonArray.add(jsonObject);//adiciona o objeto ao json array

            //coloca os elementos de um jsonarray em uma lista
            List<JsonElement> jsonList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                try {
                    jsonList.add(jsonArray.get(i));
                } finally {

                }
            }
            // Ordena a lista
            Collections.sort(jsonList, new Comparator<JsonElement>() {
                @Override
                public int compare(JsonElement p1, JsonElement p2) {
                    int pontuacao1 = p1.getAsJsonObject().get("Pontuacao").getAsInt();
                    int pontuacao2 = p2.getAsJsonObject().get("Pontuacao").getAsInt();
                    // Ordena com base na idade decrescente, se tirar o menos, deixa em ordem
                    // crescente
                    return -Integer.compare(pontuacao1, pontuacao2);
                }
            });

            JsonArray JsonArray_ordenado = new JsonArray();
            for (JsonElement element : jsonList) {
                JsonArray_ordenado.add(element);
            }

            String jsonNovo = gson.toJson(JsonArray_ordenado);

            FileWriter writer = new FileWriter(caminhoArquivo);
            writer.write(jsonNovo);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler ou salvar o arquivo JSON: " + e);
        }
    }

    public Ranking(JFrame frame) {
        painel = new Ranking_painel(frame);
    }

    private class Ranking_painel extends JPanel {
        public Ranking_painel(JFrame frame) {
            setLayout(null);
            JLabel titulo = estilo.titulo("Ranking",0);
            titulo.setBounds(250, 0, 700, 110);
            
            JLabel lbl_facil = estilo.titulo("Facil",1);
            lbl_facil.setBounds(0, 200, 300, 110);

            JLabel lbl_normal = estilo.titulo("Normal",1);
            lbl_normal.setBounds(350, 200, 300, 110);

            JLabel lbl_dificil = estilo.titulo("Dificil",1);
            lbl_dificil.setBounds(700, 200, 300, 110);

            JLabel seta=estilo.seta_voltaInicio(frame);

            try {
                String json = new String(Files.readAllBytes(Paths.get(caminhoArquivo)));

                Gson gson = new Gson();
                JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
                JLabel label[] = new JLabel[5];
                List<JsonElement> jsonList = new ArrayList<>();

                for (int i = 0; i < jsonArray.size(); i++) {
                    jsonList.add(jsonArray.get(i));
                }
                // Filtra a lista com base na dificuldade
                List<JsonElement> ranking_facil = jsonList.stream()
                        .filter(obj -> obj.getAsJsonObject().get("Dificuldade").getAsString().equals("0"))
                        .collect(Collectors.toList());
                List<JsonElement> ranking_normal = jsonList.stream()
                        .filter(obj -> obj.getAsJsonObject().get("Dificuldade").getAsString().equals("1"))
                        .collect(Collectors.toList());
                List<JsonElement> ranking_dificil = jsonList.stream()
                        .filter(obj -> obj.getAsJsonObject().get("Dificuldade").getAsString().equals("2"))
                        .collect(Collectors.toList());

                int facil_size = ranking_facil.size();
                int normal_size = ranking_normal.size();
                int dificil_size = ranking_dificil.size();
                if (facil_size > 5) {
                    facil_size = 5;
                }
                if (normal_size > 5) {
                    normal_size = 5;
                }
                if (dificil_size > 5) {
                    dificil_size = 5;
                }
                // Mostra o ranking do modo facil
                //comoo json ja esta ordenado, pode pegar apenas com base no filtro
                for (int i = 0; i < facil_size; i++) {
                    JsonObject ranking_facil_obj = ranking_facil.get(i).getAsJsonObject();
                    label[i] = estilo.titulo((i + 1) + ". " + ranking_facil_obj.get("Jogador").getAsString() +
                            ": " + ranking_facil_obj.get("Pontuacao"),2);
                    label[i].setBounds(0, 300 + 70 * i, 350, 40);
                    add(label[i]);
                }
                // Mostra o ranking do modo normal
                for (int i = 0; i < normal_size; i++) {
                    JsonObject ranking_normal_obj = ranking_normal.get(i).getAsJsonObject();
                    label[i] = estilo.titulo((i + 1) + ". " + ranking_normal_obj.get("Jogador").getAsString() +
                            ": " + ranking_normal_obj.get("Pontuacao"),2);
                    label[i].setBounds(350, 300 + 70 * i, 350, 40);
                    add(label[i]);
                }
                // Mostra o ranking do modo dificil
                for (int i = 0; i < dificil_size; i++) {
                    JsonObject ranking_dificil_obj = ranking_dificil.get(i).getAsJsonObject();
                    label[i] = estilo.titulo((i + 1) + ". " + ranking_dificil_obj.get("Jogador").getAsString() +
                            ": " + ranking_dificil_obj.get("Pontuacao"),2);
                    label[i].setBounds(700, 300 + 70 * i, 350, 40);
                    add(label[i]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            add(titulo);
            add(seta);
            add(lbl_facil);
            add(lbl_normal);
            add(lbl_dificil);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graficos = (Graphics2D) g.create();
            // instancia a imagem
            ImageIcon referencia = new ImageIcon("res\\fundoInicio.png");

            // carrega uma imagem na tela
            graficos.drawImage(referencia.getImage(), 0, 0, this);
        }
    }
}

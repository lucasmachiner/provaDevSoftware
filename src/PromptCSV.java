
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import models.Aluno;

public class PromptCSV {

    private String nomeArquivo = "./data/alunos.csv";
    private String arquivoResumo = "./data/resumo.csv";
    private ArrayList<Aluno> lista = new ArrayList<>();

    private int reprovado = 0;
    private int aprovado = 0;

    public ArrayList<Aluno> listarAluno() {

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            boolean primeiraLinha = true;

            while (((linha = leitor.readLine()) != null)) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] partes = linha.split(";");

                int matricula = Integer.parseInt(partes[0]);
                String nome = partes[1];
                double nota = Double.parseDouble(partes[2].replace(',', '.'));

                Aluno p = new Aluno(matricula, nome, nota);

                lista.add(p);
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }

        calcularExibirEstatisticas(lista);
        return lista;
    }

    private void calcularExibirEstatisticas(ArrayList<Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos para calcular estatísticas.");
            return;
        }

        double somaNotas = 0;
        double maiorNota = alunos.get(0).getNota();
        double menorNota = alunos.get(0).getNota();
        int totalAlunos = alunos.size();
        // int alunosComNotaMaiorOuIgual6 = 0;

        for (Aluno aluno : alunos) {
            double nota = aluno.getNota();
            somaNotas += nota;

            if (nota > maiorNota) {
                maiorNota = nota;
            }

            if (nota < menorNota) {
                menorNota = nota;
            }

            if (nota >= 6) {
                aprovado++;
            } else {
                reprovado++;
            }
        }

        double mediaTurma = somaNotas / totalAlunos;

        try (FileWriter escritor = new FileWriter(arquivoResumo, StandardCharsets.ISO_8859_1, true);
                BufferedWriter bufferEscritor = new BufferedWriter(escritor)) {

            // boolean arquivoExiste = new File(arquivoResumo).exists();

            bufferEscritor.write("Total Alunos;Aprovados;Reprovados;Média total;Maior nota;Menor nota\n");

            String linha = totalAlunos + ";" + aprovado + ";" + reprovado + ";" + mediaTurma + ";" + maiorNota + ";"
                    + menorNota;
            bufferEscritor.write(linha);

            System.out.println("Alunos adicionados ao arquivo com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro de escrita: " + e.getMessage());
        }
    }

}

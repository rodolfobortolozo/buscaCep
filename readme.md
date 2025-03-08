# Criação de uma Dependência para Publicação em Repositório Privado e no Maven Central

Este projeto demonstra a criação e publicação de uma dependência Java que realiza a pesquisa de um CEP utilizando a API ViaCEP e retorna as informações do endereço correspondente.

## Dependência Publicada no Maven Central
Para utilizar a dependência publicada, adicione o seguinte trecho no `pom.xml` do seu projeto:

```xml
<dependency>
    <groupId>io.github.rodolfobortolozo</groupId>
    <artifactId>buscaCep</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Exemplo de Utilização

Abaixo está um exemplo de como utilizar a dependência para buscar informações de um CEP:

```java
package br.com.rodolfo;

import io.github.rodolfobortolozo.BuscaCep;
import io.github.rodolfobortolozo.Endereco;

public class Main {
    public static void main(String[] args) {
        BuscaCep buscaCep = new BuscaCep();
        Endereco endereco = buscaCep.pesquisar("15600-001");

        System.out.println(endereco);
    }
}
```

Exemplo de saída:

```java
Endereco{cep='15600-001', logradouro='Avenida Expedicionários Brasileiros',
complemento='de 1140 ao fim - lado par', unidade='', bairro='Centro',
localidade='Fernandópolis', uf='SP', estado='São Paulo', regiao='Sudeste',
ibge='3515509', gia='3049', ddd='17', siafi='6411'}
```

---

## Publicação em Repositório Privado
Para publicar a dependência em um repositório privado (exemplo: Nexus), é necessário configurar o `settings.xml` do Maven com as credenciais do repositório:

```xml
<server>
    <id>nexus-snapshots</id>
    <username>seu-usuario</username>
    <password>sua-senha</password>
</server>
```

No `pom.xml`, adicione a tag `distributionManagement` apontando para o servidor privado:

```xml
<distributionManagement>
    <repository>
        <id>nexus-snapshots</id>
        <url>http://localhost:8081/repository/maven-hosted/</url>
    </repository>
</distributionManagement>
```

---

## Publicação no Maven Central
A dependência também pode ser publicada no [Maven Central](https://central.sonatype.com/). Para isso, é necessário seguir alguns passos adicionais.

### Plugins Necessários
Para a publicação no Maven Central, é necessário configurar os seguintes plugins no `pom.xml`:

- `maven-source-plugin`: Gera os arquivos-fonte da biblioteca.
- `maven-javadoc-plugin`: Gera a documentação Javadoc.
- `maven-gpg-plugin`: Assina os artefatos com GPG.

### Assinatura GPG
Para publicar no Maven Central, é necessário assinar digitalmente os artefatos. O processo de geração e envio da chave GPG está documentado em: [Sonatype GPG Guide](https://central.sonatype.org/publish/requirements/gpg/).

---

### Fluxo do Deploy
O processo de deploy foi configurado para dois cenários:

Repositório Privado: Ao executar o comando mvn deploy, os artefatos são enviados para o repositório privado configurado.

Maven Central: Ao executar o comando mvn deploy -P release-maven-central, os artefatos são enviados para o Maven Central.

Essa diferenciação de perfis permite controlar onde os artefatos serão publicados, dependendo do ambiente ou necessidade.

## Contribuição
Contribuições são bem-vindas! Caso queira sugerir melhorias ou corrigir problemas, sinta-se à vontade para abrir uma issue ou enviar um pull request.

---

## Licença
Este projeto é distribuído sob a licença MIT. Para mais detalhes, consulte o arquivo `LICENSE`.

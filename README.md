<h1>🏴‍☠️ One Piece API – Spring Boot 3 + Java 21 + PostgreSQL</h1>

<p>API REST para gerenciar <strong>Piratas</strong> e suas <strong>Missões</strong> no Novo Mundo.<br>
Projeto didático com arquitetura de mercado: <strong>Controllers → Services → Repositories → Entities → DTOs → Mappers</strong>,
validações, exceções globais e documentação automática com <strong>Swagger</strong>.</p>

<hr>

<h2>✅ Stack</h2>
<ul>
  <li>Java <strong>21</strong></li>
  <li>Spring Boot <strong>3.5.7</strong></li>
  <li>Spring Data JPA (Hibernate)</li>
  <li>PostgreSQL</li>
  <li>Validation (Jakarta)</li>
  <li>Lombok</li>
  <li>Springdoc OpenAPI (Swagger)</li>
</ul>

<hr>

<h2>📦 Requisitos</h2>
<ul>
  <li>JDK <strong>21</strong></li>
  <li>Maven <strong>3.9+</strong></li>
  <li>PostgreSQL <strong>&gt;= 14</strong> (rodando localmente)</li>
</ul>

<hr>

<h2>🗄️ Banco de Dados</h2>
<p>Crie o banco local (ajuste o nome se quiser):</p>

<pre><code class="language-sql">
CREATE DATABASE api_onepiece;
</code></pre>

<h3><code>application.properties</code> (commitável)</h3>
<pre><code class="language-properties">
spring.application.name=OnePiece
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.profiles.include=local
# dialeto é detectado automaticamente no Boot 3.5, não precisa setar
</code></pre>

<h3><code>application-local.properties</code> (NÃO commitar)</h3>
<p>Coloque em <code>src/main/resources/application-local.properties</code> e adicione no <code>.gitignore</code>:</p>

<pre><code class="language-bash">
# application-local*
src/main/resources/application-local.properties
</code></pre>

<p>Conteúdo (ajuste usuário/senha/porta):</p>
<pre><code class="language-properties">
spring.datasource.url=jdbc:postgresql://localhost:5432/api_onepiece
spring.datasource.username=SEU_USER
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver
</code></pre>

<hr>

<h2>▶️ Como rodar</h2>
<p>Via IntelliJ (botão verde) ou via Maven:</p>

<pre><code class="language-bash">
mvn spring-boot:run
</code></pre>

<p>Se subir corretamente, você verá algo como:</p>
<pre><code class="language-text">
Tomcat started on port 8080
Started OnePieceApplication...
</code></pre>

<hr>

<h2>📖 Swagger (documentação interativa)</h2>
<ul>
  <li>UI: <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a> (ou <code>/swagger-ui/index.html</code>)</li>
  <li>OpenAPI JSON: <a href="http://localhost:8080/v3/api-docs">http://localhost:8080/v3/api-docs</a></li>
</ul>

<p>Você consegue testar todos os endpoints direto no navegador (<strong>Try it out</strong>).</p>

<hr>

<h2>🧱 Modelagem (resumo)</h2>

<h3>🏴 Pirata</h3>
<table>
  <thead>
    <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  </thead>
  <tbody>
    <tr><td><code>id</code></td><td>UUID</td><td>Identificador</td></tr>
    <tr><td><code>nome</code></td><td>String</td><td>Nome do pirata</td></tr>
    <tr><td><code>raca</code></td><td>Enum</td><td>HUMANO, CIBORGUE, MINK, ANAO, TRITAO</td></tr>
    <tr><td><code>tripulacao</code></td><td>String</td><td>Nome da tripulação</td></tr>
    <tr><td><code>status</code></td><td>Enum</td><td>ATIVO, CAPTURADO</td></tr>
    <tr><td><code>missoes</code></td><td>OneToMany</td><td>Lista de missões do pirata</td></tr>
  </tbody>
</table>

<h3>⚔️ Missão</h3>
<table>
  <thead>
    <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  </thead>
  <tbody>
    <tr><td><code>id</code></td><td>UUID</td><td>Identificador</td></tr>
    <tr><td><code>danger</code></td><td>Enum</td><td>D, C, B, A, S</td></tr>
    <tr><td><code>tipo</code></td><td>Enum</td><td>EXPLORACAO, BATALHA_NAVAL, SAQUE</td></tr>
    <tr><td><code>status</code></td><td>Enum</td><td>CONCLUIDA, EM_ANDAMENTO</td></tr>
    <tr><td><code>pirata</code></td><td>ManyToOne</td><td>Pirata associado (obrigatório)</td></tr>
  </tbody>
</table>

<hr>

<h2>🧭 Endpoints</h2>

<h3>🏴 PIRATAS</h3>

<h4>Criar Pirata</h4>
<pre><code class="language-http">POST /piratas</code></pre>
<pre><code class="language-json">
{
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}
</code></pre>

<p><strong>201 Created</strong></p>
<pre><code class="language-json">
{
  "id": "8f2b60c0-1f47-11ef-8b6d-0242ac110002",
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}
</code></pre>

<h4>Atualizar Pirata</h4>
<pre><code class="language-http">PUT /piratas/{id}</code></pre>
<pre><code class="language-json">
{
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}
</code></pre>

<p><strong>200 OK</strong> → mesmo formato do DTO acima.</p>

<h4>Buscar por ID</h4>
<pre><code class="language-http">GET /piratas/{id}</code></pre>
<pre><code class="language-json">
{
  "id": "8f2b60c0-1f47-11ef-8b6d-0242ac110002",
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}
</code></pre>

<h4>Buscar por ID (detalhe com missões)</h4>
<pre><code class="language-http">GET /piratas/{id}/detalhe</code></pre>
<pre><code class="language-json">
{
  "id": "8f2b60c0-1f47-11ef-8b6d-0242ac110002",
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO",
  "missoes": [
    {
      "id": "5d185852-b0b7-420e-9529-f832474139c4",
      "danger": "A",
      "tipo": "BATALHA_NAVAL",
      "status": "EM_ANDAMENTO"
    }
  ]
}
</code></pre>

<h4>Buscar por Raça</h4>
<pre><code class="language-http">GET /piratas/raca/{raca}</code></pre>
<p>Exemplo: <code>/piratas/raca/HUMANO</code></p>
<p><strong>200 OK</strong> – lista de <code>PirataDTO</code>.</p>

<h4>Listar todos</h4>
<pre><code class="language-http">GET /piratas</code></pre>
<p><strong>200 OK</strong> – lista de <code>PirataDTO</code>.</p>

<h4>Deletar</h4>
<pre><code class="language-http">DELETE /piratas/{id}</code></pre>
<p><strong>204 No Content</strong></p>

<hr>

<h3>⚓ MISSÕES</h3>

<h4>Criar Missão</h4>
<pre><code class="language-http">POST /missoes</code></pre>
<pre><code class="language-json">
{
  "danger": "A",
  "tipo": "BATALHA_NAVAL",
  "status": "EM_ANDAMENTO",
  "pirataId": "8f2b60c0-1f47-11ef-8b6d-0242ac110002"
}
</code></pre>

<p><strong>201 Created</strong></p>
<pre><code class="language-json">
{
  "id": "5d185852-b0b7-420e-9529-f832474139c4",
  "danger": "A",
  "tipo": "BATALHA_NAVAL",
  "status": "EM_ANDAMENTO",
  "pirataId": "8f2b60c0-1f47-11ef-8b6d-0242ac110002"
}
</code></pre>

<h4>Atualizar Missão</h4>
<pre><code class="language-http">PUT /missoes/{id}</code></pre>
<pre><code class="language-json">
{
  "danger": "S",
  "tipo": "EXPLORACAO",
  "status": "CONCLUIDA",
  "pirataId": "8f2b60c0-1f47-11ef-8b6d-0242ac110002"
}
</code></pre>

<h4>Buscar Missão por ID</h4>
<pre><code class="language-http">GET /missoes/{id}</code></pre>
<p>→ Retorna <code>MissaoDTO</code></p>

<h4>Listar todas as Missões</h4>
<pre><code class="language-http">GET /missoes</code></pre>
<p>→ Lista de <code>MissaoDTO</code></p>

<h4>Filtros Especiais</h4>
<ul>
  <li><code>GET /missoes/danger/{nivel}</code> – Exemplo: <code>/missoes/danger/A</code></li>
  <li><code>GET /missoes/status/{status}</code> – Exemplo: <code>/missoes/status/EM_ANDAMENTO</code></li>
</ul>

<hr>

<h2>❗ Modelo de Erros (Tratamento Global)</h2>

<h3>404 – Recurso não encontrado</h3>
<pre><code class="language-json">
{
  "timestamp": "2025-11-04T22:58:12Z",
  "error": "NOT_FOUND",
  "message": "Pirata não encontrado"
}
</code></pre>

<h3>400 – Validação</h3>
<pre><code class="language-json">
{
  "timestamp": "2025-11-04T22:41:01Z",
  "error": "VALIDATION_ERROR",
  "message": "Campos inválidos",
  "fields": {
    "nome": "must not be blank",
    "tripulacao": "must not be blank"
  }
}
</code></pre>

<hr>

<h2>🔒 Validações (Regras da Marinha)</h2>
<ul>
  <li>Nenhum <strong>Pirata</strong> pode ser criado sem <code>nome</code>, <code>raca</code>, <code>tripulacao</code>, <code>status</code>.</li>
  <li>Nenhuma <strong>Missão</strong> pode ser criada sem <code>danger</code>, <code>tipo</code>, <code>status</code> e <code>pirataId</code>.</li>
  <li>Toda <strong>Missão</strong> pertence a um único <strong>Pirata</strong>.</li>
  <li>Ao consultar um <strong>Pirata</strong> no endpoint de detalhe, suas missões são exibidas.</li>
</ul>

<hr>

<h2>🧰 Dicas (Ambiente Profissional)</h2>
<ul>
  <li>Perfis: use <code>application-local.properties</code> para credenciais locais (<strong>não commitar</strong>).</li>
  <li>Use <code>EntityGraph</code> para evitar N+1 (<code>findWithMissoesById</code>).</li>
  <li>Utilize <code>DTOs + Mappers</code> para isolar entidades do payload da API.</li>
  <li>Exceptions customizadas (<code>ResourceNotFoundException</code>, <code>BusinessException</code>) + <code>@RestControllerAdvice</code>.</li>
</ul>

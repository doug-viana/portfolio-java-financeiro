#  Mercado Financeiro Backend - Portfólio

Este repositório reúne projetos de nível corporativo voltados para o ecossistema do mercado financeiro, com foco em alta performance, concorrência, compliance e segurança arquitetural utilizando Java Moderno.

##  Projetos Integrados

### 1. Ingestion Pipeline de Ativos Financeiros (`01-pipeline-ingestao-ativos`)
Pipeline de alta performance capaz de consumir APIs públicas de finanças para monitorar ativos simultaneamente.
* **Tecnologias:** Java 11+ `HttpClient`, `CompletableFuture` (Processamento Assíncrono/Paralelo), `Records` e `Text Blocks`.
* **Diferencial Técnico:** O sistema não bloqueia a execução. Ele dispara requisições assíncronas em paralelo para calcular as cotações de Dólar, Euro e Bitcoin no menor tempo possível, otimizando o uso de Threads da máquina.

# StoreApp

## Descrição
StoreApp é um aplicativo Android desenvolvido em Kotlin que permite aos usuários visualizar e gerenciar produtos favoritos. O aplicativo utiliza várias bibliotecas externas para facilitar o desenvolvimento e garantir uma experiência de usuário rica e eficiente. A aplicação foi construída com base nos conceitos de **SOLID**, **Clean Architecture**, **MVVM** e **DAO** para garantir um código organizado, escalável e fácil de manter.

## Bibliotecas Externas Utilizadas

### 1. [Picasso](https://github.com/square/picasso)
- **Versão:** 2.71828
- **Descrição:** Biblioteca para carregamento e cache de imagens.
- **Uso:** Carregar e exibir imagens de produtos.

### 2. [AndroidX](https://developer.android.com/jetpack/androidx)
- **activity-ktx:** 1.3.1
- **core-ktx:** 1.8.0
- **appcompat:** 1.7.0
- **constraintlayout:** 2.2.0
- **lifecycle-viewmodel-ktx:** 2.4.1
- **recyclerview:** 1.2.1
- **Descrição:** Conjunto de bibliotecas que ajudam a seguir as melhores práticas de desenvolvimento Android.
- **Uso:** Componentes de UI, ciclo de vida e gerenciamento de estado.

### 3. [Retrofit](https://square.github.io/retrofit/)
- **Versão:** 2.9.0
- **Descrição:** Biblioteca para fazer requisições HTTP.
- **Uso:** Comunicação com APIs RESTful.

### 4. [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- **Versão:** 1.6.4
- **Descrição:** Biblioteca para programação assíncrona.
- **Uso:** Gerenciamento de tarefas assíncronas.

### 5. [Dagger Hilt](https://dagger.dev/hilt/)
- **Versão:** 2.51.1
- **Descrição:** Biblioteca para injeção de dependência.
- **Uso:** Gerenciamento de dependências e escopo de componentes.

### 6. [Room](https://developer.android.com/jetpack/androidx/releases/room)
- **Versão:** 2.5.2
- **Descrição:** Biblioteca de persistência de dados.
- **Uso:** Gerenciamento de banco de dados SQLite.

## Padrões Aplicados

### 1. **MVVM (Model-View-ViewModel)**
- **Descrição:** Padrão de arquitetura que separa a lógica de negócios da interface do usuário.
- **Uso:** Facilita a manutenção e teste do código.

### 2. **Injeção de Dependência**
- **Descrição:** Técnica para fornecer dependências aos objetos.
- **Uso:** Utilizado com Dagger Hilt para gerenciar dependências de forma eficiente.

### 3. **View Binding**
- **Descrição:** Técnica para acessar views de forma segura e eficiente.
- **Uso:** Facilita a manipulação de views no código.

### 4. **Repository Pattern**
- **Descrição:** Padrão que abstrai a lógica de acesso a dados.
- **Uso:** Facilita a troca de fontes de dados e melhora a testabilidade.

### 5. **SOLID**
- **Descrição:** Conjunto de princípios de design de software que ajudam a criar código mais legível, manutenível e extensível.
- **Uso:** Aplicado para garantir que cada classe, função e método tenha uma única responsabilidade, seja aberto para extensão e fechado para modificação, entre outros princípios.

### 6. **Clean Architecture**
- **Descrição:** Arquitetura que promove a separação de preocupações, garantindo que o código seja mais fácil de testar e evoluir.
- **Uso:** Organiza o código em camadas, onde a lógica de negócios é separada da camada de UI, permitindo maior flexibilidade e escalabilidade.

### 7. **DAO (Data Access Object)**
- **Descrição:** Padrão de design utilizado para abstrair a comunicação com o banco de dados local, facilitando o acesso e a persistência dos dados.
- **Uso:** Implementado com a biblioteca **Room** para gerenciar a persistência de dados no banco de dados SQLite.

## Estrutura do Projeto

O projeto segue os princípios da **Clean Architecture**, que visa separar as preocupações e garantir um código mais escalável e testável. A estrutura de pastas é dividida nas seguintes camadas:

### 1. `data`
- **Descrição:** A camada **data** é responsável pela implementação dos detalhes de acesso aos dados. Ela contém as classes relacionadas à comunicação com fontes de dados externas (como APIs e bancos de dados) e implementações de repositórios. Essa camada inclui:
  - **DAOs (Data Access Objects)** para persistência local (como Room).
  - **Fontes de dados remotas** para interações com APIs, utilizando bibliotecas como Retrofit.
  - **Repositórios** que implementam as interfaces definidas na camada **domain**.
  - **Modelos de dados** que podem ser específicos para o armazenamento ou formato da API, convertendo-os para objetos que a camada **domain** entende.

### 2. `domain`
- **Descrição:** A camada **domain** contém a lógica de negócios pura da aplicação. Ela é independente de detalhes de implementação (como Retrofit ou Room) e se concentra apenas em fornecer regras de negócios e manipulação de dados. Essa camada inclui:
  - **Casos de uso (Use Cases)**, que contêm a lógica de aplicação e orquestram a interação entre os repositórios e as entidades.
  - **Interfaces de repositórios** que são implementadas na camada **data**. Essas interfaces definem as operações para obter ou manipular dados, garantindo que o **domain** não dependa de implementações específicas.

### 3. `presentation`
- **Descrição:** A camada **presentation** é responsável pela interface do usuário (UI) e como as informações são apresentadas ao usuário. Ela interage com a camada **domain** para obter os dados necessários e exibi-los de maneira adequada. Essa camada inclui:
  - **ViewModels**, que servem como intermediários entre a UI e a lógica de negócios. Os ViewModels recebem dados dos casos de uso e fornecem esses dados para a UI, além de gerenciar o estado da interface.
  - **Activities/Fragments** que representam as telas e interações do usuário.
  - **Adapters e UI components** que conectam os dados da camada **presentation** com os elementos visuais da interface.

Essa organização permite que a lógica de negócios, o acesso a dados e a UI sejam completamente desacoplados, tornando o código mais modular e facilitando os testes e a manutenção do aplicativo.


## Configuração do Projeto

### Requisitos
- **Android Studio Flamingo | 2022.2.1 Patch 2**
- **JDK 17**
- **Gradle**

### Passos para Compilar
1. Clone o repositório.
2. Abra o projeto no Android Studio.
3. Sincronize o projeto com o Gradle.
4. Compile e execute o aplicativo.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.


```


```
```
OOP_RA2_3
├─ app
│  ├─ libs
│  │  └─ jaylib-6.0.1-0.jar - Raylib, biblioteca grafica usada 
│  └─ src
│     └─ main
│        ├─ java
│        │  ├─ engine - Pacote engine, lida com colisões e logica principal do jogo
│        │  │  ├─ Circle.java - Determina forma de um circulo
│        │  │  ├─ Collider.java - Logica de colisão, pega uma forma (Circle ou Rect) e usa ela para testar colisões com outras formas
│        │  │  ├─ EDirection.java - Enum para direção de movimento
│        │  │  ├─ EGameState.java - Enum que determina o estado do jogo
│        │  │  ├─ GameConfig.java - Classe onde os dados do P1 são salvos
│        │  │  ├─ GameObject.java - Classe abstrata principal que todos os objetos visiveis no jogo herdam
│        │  │  ├─ GameTimer.java - Timer usado no jogo
│        │  │  ├─ IDamageable.java - Interface 
│        │  │  ├─ Rect.java - Determina a forma de um retangulo
│        │  │  └─ Shape.java - Determina uma forma generica
│        │  ├─ entities - Pacote de entidades usadas no jogo
│        │  │  ├─ enemy - Pacote de inimigos
│        │  │  │  ├─ Alien.java - Inimigo Alien
│        │  │  │  ├─ Asteroid.java - Inimigo Asteroide
│        │  │  │  └─ Enemy.java - Classe abstrata enemy que todos os inimigos herdam dela
│        │  │  ├─ Player.java - Player
│        │  │  └─ Projectile.java - Projetil
│        │  ├─ P1
│        │  │  ├─ config.txt - Arquivo lido pelo P1
│        │  │  ├─ config_jogo.dat - Arquivo binario gerado pelo P1
│        │  │  └─ P1.java - Proprio programa P1
│        │  └─ P2
│        │     ├─ P2.java - Proprio programa P2
│        │     ├─ P2LogWriter.java - Classe que escreve os arquivos de resultado final e resultado parcial
│        │     ├─ resultado_final.csv 
│        │     └─ resultado_parcial.csv
│        └─ resources
├─ gradle
│  ├─ libs.versions.toml
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradle.properties
├─ gradlew
├─ gradlew.bat
├─ README.md
└─ resultado_parcial.csv

```
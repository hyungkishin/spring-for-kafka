# Required

### IDE

**Intellij**

- [Download Page](https://www.jetbrains.com/ko-kr/idea/download/#section=mac) - Version: Community

### Terminal

**iTerm2**

- [Download Page](https://iterm2.com/downloads.html) - Version: 3.4.9

### Homebrew

**Install**

```bash
/bin/bash -c "$(curl -fsSL [https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh](https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh))"
```

### AdoptOpenJDK

**Download**

- [Download Page](https://adoptopenjdk.net/installation.html) or [Download Page in Adoptium](https://adoptium.net/index.html)

![자신에게 맞는 Platform 선택](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f6a0c9bc-333a-4f11-8c31-b2b1d4e14034/스크린샷_2021-09-12_19.40.36.png)

자신에게 맞는 Platform 선택

**Homebrew**

```bash
$ brew tap AdoptOpenJDK/openjdk
$ brew search openjdk
$ brew install adoptopenjdk11
$ java -version
```

# Gradle

참고: [Gradle | Installation](https://gradle.org/install/)

```bash
$ brew install gradle
$ gradle -v
```

for Windows

- [Download Page](https://gradle.org/releases/)
- [7.2 Binary-only](https://gradle.org/next-steps/?version=7.2&format=bin) 다운로드 후 압축을 풀고 Path 설정

### Apache Zookeeper

- [Download Page](http://zookeeper.apache.org/releases.html) - 3.6.3
- Apache Kafka 안에 Zookeeper 3.5.9 버젼이 존재하여 그것을 사용해도 됨

```bash
$ tar -xzf apache-zookeeper-3.6.3-bin.tar.gz
$ cd apache-zookeeper-3.6.3-bin
$ cp conf/zoo_sample.cfg conf/zoo.cfg

# Run Zookeeper
$ bin/zkServer.sh start 또는 bin/zkServer.sh start-foreground
```

### Apache Kafka

- [Download Page](https://kafka.apache.org/downloads) - Scala 2.13 - kafka_2.13-2.8.0.tgz

[Apache Kafka Documentation 의 Quickstart 에서 발췌](https://kafka.apache.org/quickstart)

```bash
$ tar -xzf kafka_2.13-2.8.0.tgz
$ cd kafka_2.13-2.8.0

# Run Zookeeper
$ bin/zookeeper-server-start.sh config/zookeeper.properties

# Run Kafka
$ bin/kafka-server-start.sh config/server.properties

# Create Topic
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092

# Show List Topic
$ bin/kafka-topics.sh --list --zookeeper localhost:2181

# Describe Topic
$ bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092

# Write Message
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092

# Read Message
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092

# Remove Topic 
$ bin/kafka-topics.sh --delete --topic quickstart-events --bootstrap-server localhost:9092
```

# Optional

### oh-my-zsh

**Install**

```bash
$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
```

### Rosetta2

<aside>
💡 M1 맥북에서 adoptopenjdk11, docker (을)를 설치하기 위해 필요

</aside>

**Install**
# Azion-API

## Install the API

Adding the API to your plugin will be more complicated than usual because you need to authentificate you to github to download it

### Maven

For the authentification with maven you need to create a `settings.xml` just like the picture below.

![plot](./libs/pictures/maven-config.PNG)


When you have your settings file just copy the text below and replace `GITHUB_USERNAME`.
You need to replace `GITHUB_TOKEN` with you personal access token. If you don't know how to create an access token -> [Create a token](https://github.com/settings/tokens/new)
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/AzionTeam/AzionAPI</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>GITHUB_USERNAME</username>
      <password>GITHUB_TOKEN</password>
    </server>
  </servers>
</settings>
```

After that a basic installation of a dependency

```xml
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/AzionTeam/AzionAPI</url>
  </repository>
```

```xml
  <dependency>
    <groupId>fr.sothis.azionapi</groupId>
    <artifactId>azionapi</artifactId>
    <version>2.0-SNAPSHOT</version>
  </dependency>
```

### Gradle
To authentificate yourself with gradle just copy the text below and replace `GITHUB_USERNAME`.
You need to replace `GITHUB_TOKEN` with you personal access token. If you don't know how to create an access token -> [Create a token](https://github.com/settings/tokens/new)

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/AzionTeam/AzionAPI")
        credentials {
            username = "GITHUB_USERNAME"
            password = "GITHUB_TOKEN"
        }
    }
}
```

And after you just add the implementation

```groovy
implementation group: 'fr.sothis.azionapi', name: 'azionapi', version: '1.5-SNAPSHOT'
```

## Managing API

The API needs to be started and stopped so you gets that method :

```java
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        AzionAPI.start(this, "The Name Of Your Plugin");
    }

    @Override
    public void onDisable() {
        AzionAPI.stop();
    }
}
```

## Tool Box

### Tools :
    - Title Generator
    - Item Generator

### Title Generator

You just have to use this line to send a title :

```java
AzionAPI.getInstance().sendTitle(player, title, chatColorTitle, subtitle, chatColorSubTitle, options);
```

As you can see, I add options to parameters. You can just modify the fadeIn, stay and the fadeOut of the title.

```java
new TitleOptions(fadeIn: 2,stay: 5,fadeOut: 10)
```

### Item Builder

I added my personal ItemBuilder to the API.
To create an itemstack you just have to do this :

```java
ItemStack item = new ItemBuilder(material).toItemStack();
```

Just watch the class [ItemBuilder](https://github.com/AzionMC-team/Azion-API/blob/main/src/main/java/fr/azion/sothis/api/tools/ItemBuilder.java) to see all you can do with this tool !

## Database

### Managers

With the api you can edit or get user or grade and many other things incoming.
To the access to the manager you just have to write this line, of course change the end to get another manager :D

```java
AzionAPI.getInstance().getUserManager();
```

### Methods

In all managers you have similar methods like :

Get an object from the database:
```java
userManager.getUser(uuid);
```

Verify if your object exists in the database:
```java
userManager.isRegistered(uuid);
```

Update the object in the database:
```java
userManager.updateUser(user)
```

And a shortcut to fast update your object
```java
userManager.updateUser(uuid, user -> {
    user.setName("Chocolat");
})
```

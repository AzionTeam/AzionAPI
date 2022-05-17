# Azion-API
 
## Install the API

### Gradle
Coming Soon

## Tool Box

### Tools :
    - Title Generator
    - Item Generator

### Title Generator

Can just have to use this line to send a title :

```java
AzionAPI.getInstance().sendTitle(player, title, chatColorTitle, subtitle, chatColorSubTitle, options);
```

Has you can see, I add options to parameters. You can just modify the fadeIn, stay and the fadeOut of the title.

```java
new TitleOptions(fadeIn: 2,stay: 5,fadeOut: 10)
```

### Item Builder

I add my personal ItemBuilder to the API.  
For create an itemstack you just have to do this :

```java
ItemStack item = new ItemBuilder(material).toItemStack();
```

Just watch the class [ItemBuilder](https://github.com/AzionMC-team/Azion-API/blob/main/src/main/java/fr/azion/sothis/api/tools/ItemBuilder.java) to see all you can do with this tool !

## Database

### Managers

With the api you can edit or get user or grade and other in the future.  
To the access to the manager you just have to write this line, of course change the end to get an other manager :D

```java
AzionAPI.getInstance().getUserManager();
```

### Methods

In all managers you have similar method like :

Get an object from the database:
```java
userManager.getUser(uuid);
```

Verify if your object exist in the database:
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

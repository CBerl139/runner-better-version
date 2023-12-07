# runner-better-version
ENSEA TD Project runner
"better version" because previous version was bad so i had to restructure the code from scratch

# How to import this project in IntelliJ IDE :

1. In the IDE, go to `File > New > Project from Version Control`, under URL paste this repo's url (found under `<>Code > HTTPS` on github)
2. Make sure you have javafx downloaded in a folder where you can find it
3. Go to `File > Project Structure > Libraries > Add`, and copy the path of the /lib folder of javafx
4. In the top right, click on `Main > Edit configuration > Main`, under VM options, copy this :
```
--module-path <./lib file path> --add-modules javafx.controls
```
Make sure you replace `<./lib file path>` with your /lib folder's path

You can now run the code. Everything should work fine.
If the IDE tells you that there is a problem with the JDK, click on solve automatically and wait for the download to end, then run the code again

## Things that could be improved
- I didn't know how to make the hero shoot and infinite amount of bullets so I made him only shoot 3 and modifiied the already-existing ones.
- Some classes could be removed, especially `HeroAnimation`, `FoeAnimation` and `BulletAnimation`, which could be integrated inside the `Hero`, `Foe`, and `Bullet` classes, and thus allow for more inherintance usage.
`FoeList` could also be replaced by an `ArrayList<Foe>` attribute inside the `GameScene` class. `InputManager` is more of a utility class which could (and maybe should) be removed, moving its content to `GameScene` constructor instead.
- Instead of recreating a new `Rectangle2D` for the `spriteArea` of the hero and foe, they could be precreated inside an `ArrayList<Rectangle2D>` which could then be accessed, which would avoid a new object creation at every `update()` call.

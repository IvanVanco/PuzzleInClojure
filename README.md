#                                              Puzzle(Slagalica) in Clojure
Simulation of the Serbian game called Slagalica,
where player chooses 12 letters of an alphabet from random generator, and then
during the specific time, they need to make longest word to win.

# Project structure

1. Main view namespace with main window; 

2. Message view namespace with all kind of different message views;

3. Repository namespace with all data (possiblities, character list mappings and dictionary); 

4. Converting namespace for storing algorithm for converting latin to cyrillic;

5. Checking namespace (Letter and Word Controller). 

6. Dictionary namespace;

7. Scoring namespace for calculating score based on different word sizes;  

8. Timer namespace with atomic fields for tracking passed game time; 

9. Partial drawing namespace (include Letter position, Inserted letter, part-Drawn letters, Generator in future);

10. Message Controller namespace, for future integration with Engine classes.

11. Documentation - find on https://ivanvanco.github.io/PuzzleInClojure/


# What is new

 1. API Documentation - on https://ivanvanco.github.io/PuzzleInClojure/

 2. Added external library support for creating documentation.
    Find more info on this link https://github.com/funcool/codeina 
 
 3. Changed project's name to Puzzle 
 
 4. Fixes in MessageView namespace



**All classes are tested in REPL and using main java function.

# What I learned

1. Function style programming- More got used to it, little slower then i thought :). Used a lot clojure.core, clojure.string and clojure.data functions, and
   wrote my owns, with help of loop/recur and map functions.
2. Namespaces - same as Java packages. They are excellent organizing tool used for importing java and clojure built in libraries, and using you own ones.
3. Java interoperability - As language who runs on JVM, Clojure have great ways for creating objects, classes, contructors, using java's standard 
   and static methods and for more sophisticated features like interfaces and polymorphism.
4. Clojure's Swing integration
5. Atoms - great concept for storing state of objects. It can be changed/modified with swap! and reset! function.
6. Lein User Profiles.clj - added documentation library support and configuration globally
7. Creating dynamic documentation using 3rd party libries 

* I found cycle dependency problem in my original code between Repository namespace(SlovaRepository class) and Konvertovanja namespace (KonvertorRec). 
  Dependency is removed from Repository namespace, and transfered to future Message Engine namespace.


## Testing it out
Install Emacs Clojure editor, or you install plugins for java's IDEs, such as Cursive for Idea InteliiJ https://cursive-ide.com/
You can install Leiningen API for better management of project on this link https://leiningen.org/.
Set up REPL environment for better learning and testing  https://clojure.org/guides/repl/introduction
Happy learning and have fun!

## License

Copyright © 2019 Ivan Mitrovic

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

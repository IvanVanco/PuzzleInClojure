#                                              Puzzle(Slagalica) in Clojure
Simulation of the Serbian game called Slagalica,
where player chooses 12 letters of an alphabet from random generator, and then
during the specific time, they need to make longest word to win.


# What is new

1. Added Messsage controller namespace. It is pipe between Message views and Message Engine(will be added in future)
2. Time namespace for storing game clock model data.
3. Dictionary namespace for including:
   a) Word class- I used defrecord function to create class(it is represented as map) and contructor
      function for creating object of this type. 
   b) Dictionary model- It is represented as map of Word objects. Using initialize function will
      import Repository namespace and import dictionary from file system.
4. Drawing namespace - we have following abstactions:
   1. Letter position - for keeping records of current drawing letter index - DONE
   2. Inserted letter - model for storing entered letters - DONE
   3. Drawn letter - model for storing drawn letters - Partly done
   4. Generator - TODO
5. Controlling namespace - two main functionality includes:
   a) Controlling letters - checking for correctness of the letters entries entered by player.
   b) Controlling word - checking for semantic correctness of the word entries entered by player with available dictionary .
   

**All classes are tested in REPL and using main java function.


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

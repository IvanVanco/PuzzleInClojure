#                                              Puzzle(Slagalica) in Clojure
Simulation of the Serbian game called Slagalica,
where player chooses 12 letters of an alphabet from random generator, and then
during the specific time, they need to make longest word to win.


# What is new

1. Refactored Main view namespace. 
   a) I used function doseq to simulate for loop in Java
   b) Doto macro for evaluating instance-expr and calling all of the methods/functions
      with the supplied arguments in succession on the resulting object, returning it.
   c) -> macro for expanding into a member access (.) of the first member on the first argument, 
      followed by the next member on the result, etc. For instance:
      (.. System (getProperties) (get "os.name"))

2. Made better future organization plan of code structure using next pattern (com.foldername(s).namespace)  


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

#                                              Puzzle(Slagalica) in Clojure
Simulation of the Serbian game called Slagalica,
where player chooses 12 letters of an alphabet from random generator, and then
during the specific time, they need to make longest word to win.


# What is new

1. Added Repository namespace. This namespace is place for storing embedded data and function with IO.
   It conssists of:
   а) Vectors of latinic and cyrillic letters. They will be used for converting one letter to another.
   b) Probability map with letter as key and map as value. Map is based of: 
      1) Weighted coefficient - it represents the probability of drawing semi random letters.
      2) Boolean key - If true then that letter, if drawned, will be removed from probability distribution function
   c) Vectors for reading word dictionary and storing supervising words - I implemented Clojure's IO libraby,
      which is much easier compared to java's IO librabies. 
      

2. Added PorukeView namespace. Used for displaying various messages to the users.
   It is composed of 3 Java's classes:
   a) PotvrdaReci view - it is showed when user click on Potvrdi button on MainView, to perform checking of 
      inserted word existances in drawn letters. 
   b) Recnik view - It is triggered just after PotvrdaReci view. Frame for displaying checking of 
      inserted word existances in word dictionary and computer's longest word.
   c) IstekVremena view - It is composition of other two message views, with time trigger in mind. 
      I used atom for storing question answer for other application modules.


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

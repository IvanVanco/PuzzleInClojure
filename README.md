#                                              Puzzle(Slagalica) in Clojure
Simulation of the Serbian game called Slagalica,
where player chooses 12 letters of an alphabet from random generator, and then
during the specific time, they need to make longest word to win.


# What is new

1. Added Konvertovanje namespace. This namespace is used for converting latin letters/word into cyrillic.
   It consists of:
   а) Letters mappings - got them by importing Repository namespace.
   b) Convert function latinToCyrillic with 3 parts:
      1)Transformation function for converting word to letters - this step have some logic built into it, 
        using serbian's language rules
      2)Main function for mapping repository letters with result - conversation process
      3)Reverse function for converting word to letters
   c) Converting functions to lower and upper cases. 
      

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

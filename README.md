# AsmMachine
ASM machine lab

# Program example (bubble sort)

```
0x04, 0x00,                    //      LD D, [0]
                               // ext_loop:
0x0D, 0x01,                    //      CP D, 1
0x11, 0x00, (byte) 0x84,       //      JE end
0x0A,                          //      DEC D
0x05, 0x00,                    //      LD E, [0]
                               // int_loop:
0x0E, 0x01,                    //      CP E, 1
0x11, 0x00, 0x66,              //      JE ext_loop
0x0C,                          //      DEC E
0x06,                          //      LD A, [E]
0x0B,                          //      INC E
0x07,                          //      LD B, [E]
0x0C,                          //      DEC E
0x0F,                          //      CP A, B
0x12, 0x00, 0x6E,              //      JL int_loop
0x09,                          //      LD [E], B
0x0B,                          //      INC E
0x08,                          //      LD [E], A
0x0C,                          //      DEC E
0x10, 0x00, 0x6E               //      JP int_loop
                               // end:
                               //      NOP
```

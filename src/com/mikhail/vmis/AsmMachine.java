package com.mikhail.vmis;

public class AsmMachine {
    private byte A;
    private byte B;
    private byte C;
    private byte D;
    private byte E;
    private short PC = 100;

    private boolean eq;
    private boolean less;

    private byte[] RAM = new byte[300];

    public byte[] getRAM() {
        return RAM;
    }

    public void run() {
        while (PC < 300) {
            byte op_code = RAM[PC++];

            switch (op_code) {
                case 0x01:
                    LD_A_B();
                    break;
                case 0x02:
                    LD_B_C();
                    break;
                case 0x03:
                    LD_C_A();
                    break;
                case 0x04:
                    LD_D_addr();
                    break;
                case 0x05:
                    LD_E_addr();
                    break;
                case 0x06:
                    LD_A_addr_E();
                    break;
                case 0x07:
                    LD_B_addr_E();
                    break;
                case 0x08:
                    LD_E_addr_A();
                    break;
                case 0x09:
                    LD_E_addr_B();
                    break;
                case 0x0A:
                    DEC_D();
                    break;
                case 0x0B:
                    INC_E();
                    break;
                case 0x0C:
                    DEC_E();
                    break;
                case 0x0D:
                    CP_D_val();
                    break;
                case 0x0E:
                    CP_E_val();
                    break;
                case 0x0F:
                    CP_A_B();
                    break;
                case 0x10:
                    JP_addr();
                    break;
                case 0x11:
                    JE_addr();
                    break;
                case 0x12:
                    JL_addr();
                    break;
                default:
                    break;
            }
        }
    }

    public void loadProgram(byte[] program) {
        int ptr = 100;
        for (byte b : program) {
            RAM[ptr++] = b;
        }
    }

    public void loadData(byte[] data) {
        int ptr = 0;
        for (byte b : data) {
            RAM[ptr++] = b;
        }
    }

    private void LD_A_B() {
        A = B;
    }

    private void LD_B_C() {
        B = C;
    }

    private void LD_C_A() {
        C = A;
    }

    private void LD_D_addr() {
        byte addr = RAM[PC++];
        D = RAM[addr];
    }

    private void LD_E_addr() {
        byte addr = RAM[PC++];
        E = RAM[addr];
    }

    private void LD_A_addr_E() {
        A = RAM[E];
    }

    private void LD_B_addr_E() {
        B = RAM[E];
    }

    private void LD_E_addr_A() {
        RAM[E] = A;
    }

    private void LD_E_addr_B() {
        RAM[E] = B;
    }

    private void DEC_D() {
        D--;
    }

    private void INC_E() {
        E++;
    }

    private void DEC_E() {
        E--;
    }

    private void CP_D_val() {
        eq = D == RAM[PC];
        less = D < RAM[PC++];
    }

    private void CP_E_val() {
        eq = E == RAM[PC];
        less = E < RAM[PC++];
    }

    private void CP_A_B() {
        eq = A == B;
        less = A < B;
    }

    private void JP_addr() {
        byte high = RAM[PC++];
        byte low = RAM[PC++];
        PC = (short)((short)(high << 8) + (short)low);
    }

    private void JE_addr() {
        // klutch for test pass
        int high = RAM[PC++] & 0xFF;
        int low = RAM[PC++] & 0xFF;
        if (eq) {
            PC = (short)((short)(high << 8) + (short)low);
        }
    }

    private void JL_addr() {
        byte high = RAM[PC++];
        byte low = RAM[PC++];
        if (less) {
            PC = (short)((short)(high << 8) + (short)low);
        }
    }
}

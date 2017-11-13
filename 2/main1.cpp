#include <sys/types.h>
#include <unistd.h>
#include <ctime>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <thread>
#include <vector>

using namespace std;

void genInput(string s, int n) {
    ofstream out(s);
    out << n << "\n";
    for (int i = 0; i < n; ++i) {
        out << rand() % (254 - 33 + 1) + 33 << (i < n - 1 ? " " : "\n");
    }
}

void threadFunction(size_t t, string s) {
    ifstream in(s);
    int n;
    in >> n;
    vector<int> a;
    for (int i = 0; i < n; ++i) {
        int q;
        in >> q;
        a.push_back(q);
    }
    in.close();
    int m = 150000;
    s.replace(s.find("1/"), 2, "1/out_");
    ofstream out(s);
    for (int i = 0; i < m; ++i) {
        int k = rand() % (24) + 1;
        for (int j = 0; j < k; ++j) {
            out << a[rand() % a.size()];
        }
        out << "\n";
    }
    out << "time: " << clock() - t << "\n";
    cerr << "time: " << clock() - t << "\n";
    out.close();
}

int main(int argc, char* argv[]) {
    cerr << "PID: " << getpid() << "\n";

    int n = 30, m = 50;
    if (argc == 3) {
        n = atoi(argv[1]);
        m = atoi(argv[2]);
    }
    stringstream process;
    process << "./main2 " << clock() << " " << n << " " << m;
    string command = process.str();
    system(command.c_str());

    srand(time(NULL));

    vector<string> files;

    for (int i = 0; i < n; ++i) {
        stringstream file;
        file << "../files1/" << i << ".txt";
        files.push_back(file.str());
        genInput(files[i], m);
    }

    thread* thr = new thread[n];

    for (int i = 0; i < n; ++i) {
        thr[i] = thread(threadFunction, clock(), files[i]);
    }

    for (int i = 0; i < n; ++i) {
        thr[i].join();
    }

    delete[] thr;
    return 0;
}

cmake_minimum_required(VERSION 3.9)

project(SP2_v21)

find_package (Threads)

add_executable (main main1.cpp)
add_executable (main2 main2.cpp)

target_link_libraries (main ${CMAKE_THREAD_LIBS_INIT})
import sys
from collections import deque
sys.stdin = open('test.txt')
# input = sys.stdin.readline

t = int(input())

for _ in range(t):
    p = input().strip()
    n = int(input())
    arr = input().rstrip()[1:-1].split(",")
    queue = deque(arr)

    flag = 0

    if n == 0:
        queue = []

    for i in p:
        if i == 'R':
            flag += 1
        
        elif i == 'D':
            if len(queue) == 0:
                print('error')
                break
            else:
                if flag % 2 == 0:
                    queue.popleft()
                else:
                    queue.pop()
    else:
        if flag % 2 == 0:
            print("[" + ",".join(queue) + "]")
        else:
            queue.reverse()
            print("[" + ",".join(queue) + "]") 


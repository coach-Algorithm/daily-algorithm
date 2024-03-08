import sys
input = sys.stdin.readline

N, P = map(int, input().split())

_list = [[] for _ in range(7)]
ans = 0

for i in range(N):
    n, p = map(int, input().split())
    
    if not _list[n-1]:
        _list[n-1].append(p)
        ans += 1

    else:
        while _list[n-1] and p < _list[n-1][-1]:
            _list[n-1].pop()
            ans += 1
        if not _list[n-1] or p > _list[n-1][-1]:
            _list[n-1].append(p)
            ans += 1
        else:
            continue

print(ans)

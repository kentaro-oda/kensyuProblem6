SELECT f.fortune_name, rescnt.count FROM fortune f LEFT OUTER JOIN
(SELECT o.fortune_id,COUNT(*) FROM omikuji o INNER JOIN result r ON o.omikuji_id = r.omikuji_id
WHERE r.fortune_day >= /*dayOfHalfAYearAgo*/
AND r.fortune_day <= /*today*/
GROUP BY o.fortune_id)
rescnt ON f.fortune_id = rescnt.fortune_id WHERE f.fortune_id = /*fortuneId*/
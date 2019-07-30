SELECT r.fortune_day, f.fortune_name, o.negaigoto, o.akinai, o.gakumon FROM fortune f INNER JOIN omikuji o
ON f.fortune_id = o.fortune_id INNER JOIN result r ON o.omikuji_id = r.omikuji_id
WHERE birthday = /*sqlBirthday*/
AND fortune_day >= /*dayOfHalfAYearAgo*/
AND fortune_day <= /*today*/
ORDER BY fortune_day DESC